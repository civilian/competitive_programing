"""
Video Spider - Crawler para descargar todos los videos de un sitio web
Incluye técnicas anti-detección para simular navegación humana
Con soporte para guardar/reanudar estado y cambio de ubicación por disco lleno
"""

import yt_dlp
import requests
from bs4 import BeautifulSoup
from urllib.parse import urljoin, urlparse
import time
import random
import re
import os
import json
import shutil
import tkinter as tk
from tkinter import filedialog, messagebox, ttk, scrolledtext
from threading import Thread
from collections import deque
from datetime import datetime

# User agents reales de navegadores populares
USER_AGENTS = [
    'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36',
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36',
    'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.1 Safari/605.1.15',
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:121.0) Gecko/20100101 Firefox/121.0',
    'Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:121.0) Gecko/20100101 Firefox/121.0',
    'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36',
]

# Patrones para detectar URLs de video
VIDEO_PATTERNS = [
    r'https?://[^\s<>"\']+\.(mp4|webm|m3u8|mpd|avi|mov|mkv)(\?[^\s<>"\']*)?',
    r'https?://(?:www\.)?youtube\.com/watch\?v=[a-zA-Z0-9_-]+',
    r'https?://(?:www\.)?youtu\.be/[a-zA-Z0-9_-]+',
    r'https?://(?:www\.)?vimeo\.com/\d+',
    r'https?://(?:www\.)?dailymotion\.com/video/[a-zA-Z0-9]+',
    r'https?://(?:www\.)?twitch\.tv/videos/\d+',
    r'https?://(?:player\.)?vimeo\.com/video/\d+',
    r'https?://[^\s<>"\']*embed[^\s<>"\']*',
    r'https?://[^\s<>"\']*video[^\s<>"\']*\.(mp4|webm)',
]

# Espacio mínimo requerido en bytes (500 MB)
MIN_DISK_SPACE = 500 * 1024 * 1024


class VideoSpider:
    def __init__(self, base_url, output_dir, max_pages=100, delay_range=(2, 5), use_cookies=False, browser='chrome'):
        self.base_url = base_url
        self.domain = urlparse(base_url).netloc
        self.output_dir = output_dir
        self.max_pages = max_pages
        self.delay_range = delay_range
        self.use_cookies = use_cookies
        self.browser = browser
        
        self.visited_urls = set()
        self.video_urls = set()
        self.downloaded_videos = set()  # Videos ya descargados
        self.failed_videos = set()  # Videos que fallaron
        self.urls_to_visit = deque([base_url])
        self.session = self._create_session()
        self.log_callback = None
        self.running = False
        self.ask_new_location_callback = None  # Callback para pedir nueva ubicación
        
    def _create_session(self):
        """Crea una sesión HTTP con headers que simulan un navegador real"""
        session = requests.Session()
        
        # Headers realistas
        session.headers.update({
            'User-Agent': random.choice(USER_AGENTS),
            'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8',
            'Accept-Language': 'es-ES,es;q=0.9,en;q=0.8',
            'Accept-Encoding': 'gzip, deflate, br',
            'DNT': '1',
            'Connection': 'keep-alive',
            'Upgrade-Insecure-Requests': '1',
            'Sec-Fetch-Dest': 'document',
            'Sec-Fetch-Mode': 'navigate',
            'Sec-Fetch-Site': 'none',
            'Sec-Fetch-User': '?1',
            'Cache-Control': 'max-age=0',
        })
        
        return session
    
    def _random_delay(self):
        """Espera un tiempo aleatorio para simular comportamiento humano"""
        delay = random.uniform(*self.delay_range)
        # Ocasionalmente hacer pausas más largas (como si el usuario leyera)
        if random.random() < 0.1:
            delay *= random.uniform(2, 4)
        time.sleep(delay)
    
    def _rotate_user_agent(self):
        """Cambia el User-Agent periódicamente"""
        if random.random() < 0.3:  # 30% de probabilidad de cambiar
            self.session.headers['User-Agent'] = random.choice(USER_AGENTS)
    
    def _is_same_domain(self, url):
        """Verifica si la URL pertenece al mismo dominio"""
        parsed = urlparse(url)
        return parsed.netloc == self.domain or parsed.netloc == ''
    
    def _normalize_url(self, url, base_url):
        """Normaliza y completa URLs relativas"""
        full_url = urljoin(base_url, url)
        # Eliminar fragmentos (#)
        parsed = urlparse(full_url)
        return f"{parsed.scheme}://{parsed.netloc}{parsed.path}" + (f"?{parsed.query}" if parsed.query else "")
    
    def _log(self, message):
        """Log con callback opcional para la GUI"""
        print(message)
        if self.log_callback:
            self.log_callback(message)
    
    def _check_disk_space(self, path=None):
        """Verifica si hay suficiente espacio en disco"""
        check_path = path or self.output_dir
        try:
            usage = shutil.disk_usage(check_path)
            free_space = usage.free
            return free_space, free_space > MIN_DISK_SPACE
        except Exception:
            return 0, True  # Si no podemos verificar, asumimos que hay espacio
    
    def _format_bytes(self, bytes_val):
        """Formatea bytes a formato legible"""
        for unit in ['B', 'KB', 'MB', 'GB', 'TB']:
            if bytes_val < 1024:
                return f"{bytes_val:.2f} {unit}"
            bytes_val /= 1024
        return f"{bytes_val:.2f} PB"
    
    def _extract_video_urls(self, html, page_url):
        """Extrae URLs de videos del HTML"""
        videos_found = set()
        
        # Buscar con patrones regex
        for pattern in VIDEO_PATTERNS:
            matches = re.findall(pattern, html, re.IGNORECASE)
            for match in matches:
                if isinstance(match, tuple):
                    continue  # Saltar grupos de captura
                videos_found.add(match)
        
        # Parsear HTML para encontrar más videos
        soup = BeautifulSoup(html, 'html.parser')
        
        # Videos en tags <video>
        for video in soup.find_all('video'):
            src = video.get('src')
            if src:
                videos_found.add(self._normalize_url(src, page_url))
            for source in video.find_all('source'):
                src = source.get('src')
                if src:
                    videos_found.add(self._normalize_url(src, page_url))
        
        # Iframes (pueden contener videos embebidos)
        for iframe in soup.find_all('iframe'):
            src = iframe.get('src') or iframe.get('data-src')
            if src:
                # Verificar si es un iframe de video conocido
                if any(x in src.lower() for x in ['youtube', 'vimeo', 'dailymotion', 'twitch', 'player', 'embed', 'video']):
                    videos_found.add(self._normalize_url(src, page_url))
        
        # Links a archivos de video
        for a in soup.find_all('a', href=True):
            href = a['href'].lower()
            if any(ext in href for ext in ['.mp4', '.webm', '.avi', '.mov', '.mkv', '.m3u8']):
                videos_found.add(self._normalize_url(a['href'], page_url))
        
        # Buscar en atributos data-*
        for tag in soup.find_all(attrs={'data-video-url': True}):
            videos_found.add(self._normalize_url(tag['data-video-url'], page_url))
        for tag in soup.find_all(attrs={'data-src': True}):
            src = tag['data-src']
            if any(ext in src.lower() for ext in ['.mp4', '.webm', '.m3u8', 'video', 'player']):
                videos_found.add(self._normalize_url(src, page_url))
        
        # Buscar en scripts (JSON con URLs de video)
        for script in soup.find_all('script'):
            if script.string:
                # Buscar URLs de video en JSON embebido
                for pattern in VIDEO_PATTERNS:
                    matches = re.findall(pattern, script.string, re.IGNORECASE)
                    for match in matches:
                        if isinstance(match, str):
                            videos_found.add(match)
        
        return videos_found
    
    def _extract_page_links(self, html, page_url):
        """Extrae enlaces a otras páginas del sitio"""
        soup = BeautifulSoup(html, 'html.parser')
        links = set()
        
        for a in soup.find_all('a', href=True):
            href = a['href']
            full_url = self._normalize_url(href, page_url)
            
            # Solo seguir enlaces del mismo dominio
            if self._is_same_domain(full_url):
                # Ignorar enlaces a archivos no HTML
                if not any(full_url.lower().endswith(ext) for ext in ['.jpg', '.jpeg', '.png', '.gif', '.pdf', '.zip', '.css', '.js']):
                    links.add(full_url)
        
        return links
    
    def get_state(self):
        """Obtiene el estado actual del spider para guardarlo"""
        return {
            'version': '1.0',
            'timestamp': datetime.now().isoformat(),
            'base_url': self.base_url,
            'domain': self.domain,
            'output_dir': self.output_dir,
            'max_pages': self.max_pages,
            'delay_range': self.delay_range,
            'use_cookies': self.use_cookies,
            'browser': self.browser,
            'visited_urls': list(self.visited_urls),
            'video_urls': list(self.video_urls),
            'downloaded_videos': list(self.downloaded_videos),
            'failed_videos': list(self.failed_videos),
            'urls_to_visit': list(self.urls_to_visit),
        }
    
    def load_state(self, state):
        """Carga un estado previamente guardado"""
        self.base_url = state.get('base_url', self.base_url)
        self.domain = state.get('domain', self.domain)
        # No sobreescribimos output_dir para permitir cambio de ubicación
        self.max_pages = state.get('max_pages', self.max_pages)
        self.delay_range = tuple(state.get('delay_range', self.delay_range))
        self.use_cookies = state.get('use_cookies', self.use_cookies)
        self.browser = state.get('browser', self.browser)
        self.visited_urls = set(state.get('visited_urls', []))
        self.video_urls = set(state.get('video_urls', []))
        self.downloaded_videos = set(state.get('downloaded_videos', []))
        self.failed_videos = set(state.get('failed_videos', []))
        self.urls_to_visit = deque(state.get('urls_to_visit', []))
        
        self._log(f"📂 Estado cargado:")
        self._log(f"   - URLs visitadas: {len(self.visited_urls)}")
        self._log(f"   - Videos encontrados: {len(self.video_urls)}")
        self._log(f"   - Videos descargados: {len(self.downloaded_videos)}")
        self._log(f"   - Videos pendientes: {len(self.video_urls) - len(self.downloaded_videos)}")
        self._log(f"   - Páginas por visitar: {len(self.urls_to_visit)}")
    
    def save_state_to_file(self, filepath):
        """Guarda el estado en un archivo JSON"""
        state = self.get_state()
        with open(filepath, 'w', encoding='utf-8') as f:
            json.dump(state, f, indent=2, ensure_ascii=False)
        self._log(f"💾 Estado guardado en: {filepath}")
    
    def load_state_from_file(self, filepath):
        """Carga el estado desde un archivo JSON"""
        with open(filepath, 'r', encoding='utf-8') as f:
            state = json.load(f)
        self.load_state(state)
        return state
    
    def crawl(self):
        """Ejecuta el crawler"""
        self.running = True
        pages_crawled = len(self.visited_urls)  # Continuar desde donde quedó
        
        self._log(f"🕷️ Iniciando spider en: {self.base_url}")
        self._log(f"📁 Directorio de salida: {self.output_dir}")
        
        if pages_crawled > 0:
            self._log(f"🔄 Reanudando desde página {pages_crawled}")
        
        while self.urls_to_visit and pages_crawled < self.max_pages and self.running:
            current_url = self.urls_to_visit.popleft()
            
            if current_url in self.visited_urls:
                continue
            
            self.visited_urls.add(current_url)
            pages_crawled += 1
            
            self._log(f"\n📄 [{pages_crawled}/{self.max_pages}] Visitando: {current_url}")
            
            try:
                # Rotar User-Agent ocasionalmente
                self._rotate_user_agent()
                
                # Simular referrer del sitio
                self.session.headers['Referer'] = self.base_url
                
                response = self.session.get(current_url, timeout=30, allow_redirects=True)
                
                if response.status_code == 200:
                    html = response.text
                    
                    # Extraer videos
                    videos = self._extract_video_urls(html, current_url)
                    new_videos = videos - self.video_urls
                    if new_videos:
                        self._log(f"🎬 Encontrados {len(new_videos)} videos nuevos")
                        self.video_urls.update(new_videos)
                    
                    # Extraer enlaces para seguir crawleando
                    links = self._extract_page_links(html, current_url)
                    new_links = links - self.visited_urls
                    for link in new_links:
                        if link not in self.urls_to_visit:
                            self.urls_to_visit.append(link)
                    
                elif response.status_code == 429:
                    self._log(f"⚠️ Rate limited. Esperando más tiempo...")
                    time.sleep(random.uniform(30, 60))
                    self.urls_to_visit.appendleft(current_url)
                    self.visited_urls.discard(current_url)
                    pages_crawled -= 1
                else:
                    self._log(f"⚠️ Error {response.status_code}")
                    
            except requests.exceptions.RequestException as e:
                self._log(f"❌ Error de conexión: {str(e)[:50]}")
            
            # Delay humano entre requests
            self._random_delay()
        
        self._log(f"\n✅ Crawling completado!")
        self._log(f"📊 Páginas visitadas: {pages_crawled}")
        self._log(f"🎬 Videos encontrados: {len(self.video_urls)}")
        
        return self.video_urls
    
    def download_videos(self):
        """Descarga todos los videos encontrados"""
        # Videos pendientes = encontrados - descargados
        pending_videos = self.video_urls - self.downloaded_videos
        
        if not pending_videos:
            self._log("❌ No hay videos pendientes para descargar")
            return
        
        self._log(f"\n📥 Iniciando descarga de {len(pending_videos)} videos pendientes...")
        self._log(f"   (Ya descargados previamente: {len(self.downloaded_videos)})")
        
        downloaded = 0
        failed = 0
        
        for i, video_url in enumerate(pending_videos, 1):
            if not self.running:
                break
            
            # Verificar espacio en disco antes de cada descarga
            free_space, has_space = self._check_disk_space()
            
            if not has_space:
                self._log(f"\n⚠️ ¡Espacio en disco bajo! ({self._format_bytes(free_space)} disponibles)")
                self._log(f"📊 Videos descargados hasta ahora: {downloaded}")
                
                # Pedir nueva ubicación
                if self.ask_new_location_callback:
                    new_location = self.ask_new_location_callback(free_space)
                    if new_location:
                        self.output_dir = new_location
                        self._log(f"📁 Nueva ubicación: {self.output_dir}")
                        # Verificar espacio en nueva ubicación
                        free_space, has_space = self._check_disk_space()
                        if not has_space:
                            self._log(f"❌ La nueva ubicación también tiene poco espacio")
                            continue
                    else:
                        self._log("🛑 Descarga pausada por falta de espacio")
                        break
            
            self._log(f"\n📥 [{i}/{len(pending_videos)}] Descargando: {video_url[:80]}...")
            
            ydl_opts = {
                'format': 'bestvideo+bestaudio/best',
                'outtmpl': f'{self.output_dir}/%(title)s.%(ext)s',
                'merge_output_format': 'mp4',
                'ignoreerrors': True,
                'no_warnings': True,
                'quiet': False,
                'retries': 3,
                'fragment_retries': 3,
                # Anti-detección
                'sleep_interval': random.randint(1, 3),
                'max_sleep_interval': random.randint(5, 10),
                'sleep_interval_requests': random.randint(1, 2),
            }
            
            # Añadir cookies si está habilitado
            if self.use_cookies:
                ydl_opts['cookiesfrombrowser'] = (self.browser,)
            
            try:
                with yt_dlp.YoutubeDL(ydl_opts) as ydl:
                    ydl.download([video_url])
                downloaded += 1
                self.downloaded_videos.add(video_url)
                self._log(f"✅ Descargado correctamente")
            except OSError as e:
                # Error de espacio en disco
                if "No space left" in str(e) or "disk full" in str(e).lower():
                    self._log(f"❌ Error: Disco lleno")
                    self.failed_videos.add(video_url)
                    
                    # Pedir nueva ubicación
                    if self.ask_new_location_callback:
                        new_location = self.ask_new_location_callback(0)
                        if new_location:
                            self.output_dir = new_location
                            self._log(f"📁 Nueva ubicación: {self.output_dir}")
                            # Reintentar este video
                            self.failed_videos.discard(video_url)
                            continue
                        else:
                            self._log("🛑 Descarga pausada por falta de espacio")
                            break
                else:
                    failed += 1
                    self.failed_videos.add(video_url)
                    self._log(f"❌ Error: {str(e)[:50]}")
            except Exception as e:
                failed += 1
                self.failed_videos.add(video_url)
                self._log(f"❌ Error: {str(e)[:50]}")
            
            # Delay entre descargas
            self._random_delay()
        
        self._log(f"\n🏁 Descarga completada!")
        self._log(f"✅ Exitosos: {downloaded}")
        self._log(f"❌ Fallidos: {failed}")
        self._log(f"📊 Total descargados: {len(self.downloaded_videos)}")
        self._log(f"📊 Pendientes: {len(self.video_urls - self.downloaded_videos)}")
    
    def stop(self):
        """Detiene el crawler"""
        self.running = False
        self._log("🛑 Deteniendo spider...")


class SpiderGUI:
    def __init__(self):
        self.root = tk.Tk()
        self.root.title("🕷️ Video Spider - Crawler de Videos")
        self.root.geometry("750x700")
        self.root.configure(bg='#1a1a2e')
        
        self.spider = None
        self.spider_thread = None
        self.state_file = None
        
        self._setup_styles()
        self._create_widgets()
    
    def _setup_styles(self):
        style = ttk.Style()
        style.theme_use('clam')
        style.configure('TFrame', background='#1a1a2e')
        style.configure('TLabel', background='#1a1a2e', foreground='#eee', font=('Helvetica', 10))
        style.configure('TButton', font=('Helvetica', 10, 'bold'))
        style.configure('TEntry', font=('Helvetica', 10))
        style.configure('TCheckbutton', background='#1a1a2e', foreground='#eee')
        style.configure('TLabelframe', background='#1a1a2e', foreground='#00d9ff')
        style.configure('TLabelframe.Label', background='#1a1a2e', foreground='#00d9ff', font=('Helvetica', 10, 'bold'))
    
    def _create_widgets(self):
        # Frame principal
        main_frame = ttk.Frame(self.root, padding=15)
        main_frame.pack(fill='both', expand=True)
        
        # Título
        title_label = tk.Label(main_frame, text="🕷️ Video Spider", font=('Helvetica', 20, 'bold'),
                              bg='#1a1a2e', fg='#00d9ff')
        title_label.pack(pady=(0, 10))
        
        # URL del sitio
        url_frame = ttk.LabelFrame(main_frame, text="Sitio Web", padding=10)
        url_frame.pack(fill='x', pady=5)
        
        ttk.Label(url_frame, text="URL base:").pack(anchor='w')
        self.url_entry = ttk.Entry(url_frame, width=70)
        self.url_entry.pack(fill='x', pady=5)
        self.url_entry.insert(0, "https://")
        
        # Configuración
        config_frame = ttk.LabelFrame(main_frame, text="Configuración", padding=10)
        config_frame.pack(fill='x', pady=5)
        
        # Máximo de páginas
        pages_frame = ttk.Frame(config_frame)
        pages_frame.pack(fill='x', pady=2)
        ttk.Label(pages_frame, text="Máximo de páginas:").pack(side='left')
        self.max_pages_var = tk.StringVar(value="50")
        ttk.Entry(pages_frame, textvariable=self.max_pages_var, width=10).pack(side='left', padx=10)
        
        # Delay
        delay_frame = ttk.Frame(config_frame)
        delay_frame.pack(fill='x', pady=2)
        ttk.Label(delay_frame, text="Delay entre requests (seg):").pack(side='left')
        self.delay_min_var = tk.StringVar(value="2")
        self.delay_max_var = tk.StringVar(value="5")
        ttk.Entry(delay_frame, textvariable=self.delay_min_var, width=5).pack(side='left', padx=5)
        ttk.Label(delay_frame, text="-").pack(side='left')
        ttk.Entry(delay_frame, textvariable=self.delay_max_var, width=5).pack(side='left', padx=5)
        
        # Cookies
        cookies_frame = ttk.Frame(config_frame)
        cookies_frame.pack(fill='x', pady=5)
        self.use_cookies_var = tk.BooleanVar(value=False)
        ttk.Checkbutton(cookies_frame, text="Usar cookies del navegador", variable=self.use_cookies_var).pack(side='left')
        
        self.browser_var = tk.StringVar(value='chrome')
        browsers = ['chrome', 'firefox', 'safari', 'edge', 'opera', 'brave']
        browser_combo = ttk.Combobox(cookies_frame, textvariable=self.browser_var, values=browsers, state='readonly', width=12)
        browser_combo.pack(side='left', padx=10)
        
        # Directorio de salida
        output_frame = ttk.LabelFrame(main_frame, text="Directorio de Salida", padding=10)
        output_frame.pack(fill='x', pady=5)
        
        output_inner = ttk.Frame(output_frame)
        output_inner.pack(fill='x')
        self.output_var = tk.StringVar()
        ttk.Entry(output_inner, textvariable=self.output_var, width=55).pack(side='left', fill='x', expand=True)
        ttk.Button(output_inner, text="📁 Seleccionar", command=self._select_output).pack(side='left', padx=5)
        
        # Mostrar espacio disponible
        self.disk_space_label = tk.Label(output_frame, text="", bg='#1a1a2e', fg='#aaa', font=('Helvetica', 9))
        self.disk_space_label.pack(anchor='w', pady=(5, 0))
        self.output_var.trace_add('write', self._update_disk_space)
        
        # Botones de estado (guardar/cargar)
        state_frame = ttk.LabelFrame(main_frame, text="Estado (Guardar/Reanudar)", padding=10)
        state_frame.pack(fill='x', pady=5)
        
        state_buttons = ttk.Frame(state_frame)
        state_buttons.pack(fill='x')
        
        tk.Button(state_buttons, text="💾 Guardar Estado", command=self._save_state,
                  bg='#5352ed', fg='white', font=('Helvetica', 10, 'bold'),
                  padx=15, pady=5).pack(side='left', padx=5)
        
        tk.Button(state_buttons, text="📂 Cargar Estado", command=self._load_state,
                  bg='#ffa502', fg='#1a1a2e', font=('Helvetica', 10, 'bold'),
                  padx=15, pady=5).pack(side='left', padx=5)
        
        self.state_label = tk.Label(state_frame, text="Sin estado cargado", bg='#1a1a2e', fg='#888', font=('Helvetica', 9))
        self.state_label.pack(anchor='w', pady=(5, 0))
        
        # Botones de control
        buttons_frame = ttk.Frame(main_frame)
        buttons_frame.pack(fill='x', pady=10)
        
        self.start_btn = tk.Button(buttons_frame, text="🚀 Iniciar Spider", command=self._start_spider,
                                   bg='#00d9ff', fg='#1a1a2e', font=('Helvetica', 11, 'bold'),
                                   padx=20, pady=8)
        self.start_btn.pack(side='left', padx=5)
        
        self.stop_btn = tk.Button(buttons_frame, text="🛑 Detener", command=self._stop_spider,
                                  bg='#ff4757', fg='white', font=('Helvetica', 11, 'bold'),
                                  padx=20, pady=8, state='disabled')
        self.stop_btn.pack(side='left', padx=5)
        
        self.download_btn = tk.Button(buttons_frame, text="📥 Descargar Videos", command=self._download_videos,
                                      bg='#2ed573', fg='#1a1a2e', font=('Helvetica', 11, 'bold'),
                                      padx=20, pady=8, state='disabled')
        self.download_btn.pack(side='left', padx=5)
        
        # Log
        log_frame = ttk.LabelFrame(main_frame, text="Log", padding=10)
        log_frame.pack(fill='both', expand=True, pady=5)
        
        self.log_text = scrolledtext.ScrolledText(log_frame, height=10, bg='#0f0f23', fg='#00ff00',
                                                   font=('Monaco', 9), insertbackground='#00ff00')
        self.log_text.pack(fill='both', expand=True)
    
    def _update_disk_space(self, *args):
        """Actualiza el indicador de espacio en disco"""
        path = self.output_var.get()
        if path and os.path.exists(path):
            try:
                usage = shutil.disk_usage(path)
                free = usage.free
                total = usage.total
                pct = (free / total) * 100
                
                # Color según espacio disponible
                if pct > 20:
                    color = '#2ed573'
                elif pct > 10:
                    color = '#ffa502'
                else:
                    color = '#ff4757'
                
                self.disk_space_label.config(
                    text=f"💾 Espacio disponible: {self._format_bytes(free)} ({pct:.1f}%)",
                    fg=color
                )
            except Exception:
                self.disk_space_label.config(text="", fg='#aaa')
        else:
            self.disk_space_label.config(text="", fg='#aaa')
    
    def _format_bytes(self, bytes_val):
        """Formatea bytes a formato legible"""
        for unit in ['B', 'KB', 'MB', 'GB', 'TB']:
            if bytes_val < 1024:
                return f"{bytes_val:.2f} {unit}"
            bytes_val /= 1024
        return f"{bytes_val:.2f} PB"
    
    def _select_output(self):
        directory = filedialog.askdirectory(title="Seleccionar directorio de salida")
        if directory:
            self.output_var.set(directory)
    
    def _ask_new_location(self, free_space):
        """Callback para pedir nueva ubicación cuando el disco está lleno"""
        result = messagebox.askyesno(
            "⚠️ Espacio en Disco Bajo",
            f"El espacio disponible es muy bajo ({self._format_bytes(free_space)}).\n\n"
            "¿Deseas seleccionar otra ubicación para continuar?\n\n"
            "(El estado se guardará automáticamente)"
        )
        
        if result:
            # Guardar estado automáticamente
            if self.spider:
                auto_save_path = os.path.join(
                    os.path.dirname(self.output_var.get()),
                    f"spider_state_autosave_{datetime.now().strftime('%Y%m%d_%H%M%S')}.json"
                )
                try:
                    self.spider.save_state_to_file(auto_save_path)
                except Exception:
                    pass
            
            new_dir = filedialog.askdirectory(title="Seleccionar nueva ubicación")
            if new_dir:
                self.output_var.set(new_dir)
                return new_dir
        
        return None
    
    def _log(self, message):
        self.log_text.insert(tk.END, message + "\n")
        self.log_text.see(tk.END)
        self.root.update_idletasks()
    
    def _save_state(self):
        """Guarda el estado actual del spider"""
        if not self.spider:
            messagebox.showwarning("Advertencia", "No hay un spider activo para guardar")
            return
        
        filepath = filedialog.asksaveasfilename(
            title="Guardar estado del spider",
            defaultextension=".json",
            filetypes=[("JSON files", "*.json"), ("All files", "*.*")],
            initialfile=f"spider_state_{datetime.now().strftime('%Y%m%d_%H%M%S')}.json"
        )
        
        if filepath:
            try:
                self.spider.save_state_to_file(filepath)
                self.state_file = filepath
                self.state_label.config(text=f"Estado guardado: {os.path.basename(filepath)}", fg='#2ed573')
                messagebox.showinfo("Éxito", "Estado guardado correctamente")
            except Exception as e:
                messagebox.showerror("Error", f"Error al guardar: {str(e)}")
    
    def _load_state(self):
        """Carga un estado guardado previamente"""
        filepath = filedialog.askopenfilename(
            title="Cargar estado del spider",
            filetypes=[("JSON files", "*.json"), ("All files", "*.*")]
        )
        
        if filepath:
            try:
                with open(filepath, 'r', encoding='utf-8') as f:
                    state = json.load(f)
                
                # Actualizar campos de la GUI
                self.url_entry.delete(0, tk.END)
                self.url_entry.insert(0, state.get('base_url', 'https://'))
                self.max_pages_var.set(str(state.get('max_pages', 50)))
                delay_range = state.get('delay_range', [2, 5])
                self.delay_min_var.set(str(delay_range[0]))
                self.delay_max_var.set(str(delay_range[1]))
                self.use_cookies_var.set(state.get('use_cookies', False))
                self.browser_var.set(state.get('browser', 'chrome'))
                
                # Preguntar si quiere usar la misma ubicación o una nueva
                use_same = messagebox.askyesno(
                    "Ubicación de salida",
                    f"El estado anterior usaba:\n{state.get('output_dir', 'N/A')}\n\n"
                    "¿Deseas usar la misma ubicación?\n\n"
                    "(Selecciona 'No' para elegir una nueva)"
                )
                
                if use_same and state.get('output_dir'):
                    self.output_var.set(state.get('output_dir', ''))
                else:
                    new_dir = filedialog.askdirectory(title="Seleccionar directorio de salida")
                    if new_dir:
                        self.output_var.set(new_dir)
                
                self.state_file = filepath
                self.state_label.config(
                    text=f"Estado cargado: {os.path.basename(filepath)} | "
                         f"Videos: {len(state.get('video_urls', []))} | "
                         f"Descargados: {len(state.get('downloaded_videos', []))}",
                    fg='#ffa502'
                )
                
                # Habilitar botones si hay videos
                if state.get('video_urls'):
                    self.download_btn.config(state='normal')
                
                self._log(f"\n📂 Estado cargado desde: {filepath}")
                self._log(f"   - Videos encontrados: {len(state.get('video_urls', []))}")
                self._log(f"   - Videos descargados: {len(state.get('downloaded_videos', []))}")
                self._log(f"   - Páginas visitadas: {len(state.get('visited_urls', []))}")
                
            except Exception as e:
                messagebox.showerror("Error", f"Error al cargar: {str(e)}")
    
    def _start_spider(self):
        url = self.url_entry.get().strip()
        output_dir = self.output_var.get().strip()
        
        if not url or url == "https://":
            messagebox.showerror("Error", "Por favor ingresa una URL válida")
            return
        
        if not output_dir:
            messagebox.showerror("Error", "Por favor selecciona un directorio de salida")
            return
        
        try:
            max_pages = int(self.max_pages_var.get())
            delay_min = float(self.delay_min_var.get())
            delay_max = float(self.delay_max_var.get())
        except ValueError:
            messagebox.showerror("Error", "Los valores de configuración deben ser números")
            return
        
        # Crear spider
        self.spider = VideoSpider(
            base_url=url,
            output_dir=output_dir,
            max_pages=max_pages,
            delay_range=(delay_min, delay_max),
            use_cookies=self.use_cookies_var.get(),
            browser=self.browser_var.get()
        )
        self.spider.log_callback = self._log
        self.spider.ask_new_location_callback = self._ask_new_location
        
        # Cargar estado si existe
        if self.state_file:
            try:
                self.spider.load_state_from_file(self.state_file)
            except Exception as e:
                self._log(f"⚠️ No se pudo cargar el estado: {str(e)}")
        
        # UI state
        self.start_btn.config(state='disabled')
        self.stop_btn.config(state='normal')
        self.download_btn.config(state='disabled')
        self.log_text.delete(1.0, tk.END)
        
        # Ejecutar en thread separado
        self.spider_thread = Thread(target=self._run_spider)
        self.spider_thread.start()
    
    def _run_spider(self):
        self.spider.crawl()
        self.root.after(0, self._spider_finished)
    
    def _spider_finished(self):
        self.start_btn.config(state='normal')
        self.stop_btn.config(state='disabled')
        if self.spider and self.spider.video_urls:
            self.download_btn.config(state='normal')
            self.state_label.config(
                text=f"Videos encontrados: {len(self.spider.video_urls)} | "
                     f"Descargados: {len(self.spider.downloaded_videos)}",
                fg='#00d9ff'
            )
    
    def _stop_spider(self):
        if self.spider:
            self.spider.stop()
            # Guardar estado automáticamente al detener
            auto_save = messagebox.askyesno(
                "Guardar Estado",
                "¿Deseas guardar el estado actual antes de detener?\n"
                "(Podrás reanudar después)"
            )
            if auto_save:
                self._save_state()
    
    def _download_videos(self):
        if not self.spider:
            return
        
        output_dir = self.output_var.get().strip()
        if output_dir:
            self.spider.output_dir = output_dir
        
        self.start_btn.config(state='disabled')
        self.stop_btn.config(state='normal')
        self.download_btn.config(state='disabled')
        
        thread = Thread(target=self._run_download)
        thread.start()
    
    def _run_download(self):
        self.spider.download_videos()
        self.root.after(0, self._download_finished)
    
    def _download_finished(self):
        self.start_btn.config(state='normal')
        self.stop_btn.config(state='disabled')
        self.download_btn.config(state='normal')
        
        if self.spider:
            pending = len(self.spider.video_urls - self.spider.downloaded_videos)
            self.state_label.config(
                text=f"Descargados: {len(self.spider.downloaded_videos)} | Pendientes: {pending}",
                fg='#2ed573' if pending == 0 else '#ffa502'
            )
        
        messagebox.showinfo("Completado", "Descarga de videos finalizada")
    
    def run(self):
        self.root.mainloop()


if __name__ == "__main__":
    app = SpiderGUI()
    app.run()
