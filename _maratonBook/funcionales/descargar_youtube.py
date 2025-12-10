import yt_dlp
import tkinter as tk
from tkinter import filedialog, messagebox, ttk

# Lista de navegadores soportados para cookies
BROWSERS = ['chrome', 'firefox', 'safari', 'edge', 'opera', 'brave', 'chromium']

# Función para descargar el video
def descargar_video():
    # Obtener el enlace ingresado
    url = url_entry.get()
    if not url:
        messagebox.showerror("Error", "Por favor ingresa un enlace.")
        return

    # Seleccionar el directorio de salida
    output_dir = filedialog.askdirectory(title="Seleccionar directorio de salida")
    if not output_dir:
        messagebox.showerror("Error", "Por favor selecciona un directorio de salida.")
        return

    # Obtener el navegador seleccionado para cookies
    browser = browser_var.get()
    usar_cookies = usar_cookies_var.get()

    ydl_opts = {
        'format': 'bestvideo+bestaudio/best',         # Mejor calidad de video y audio
        'outtmpl': f'{output_dir}/%(title)s.%(ext)s', # Directorio y nombre del archivo
        'merge_output_format': 'mp4',                 # Formato final en MP4
        'ignoreerrors': True,                         # Ignorar errores durante la descarga
        'postprocessors': [{
            'key': 'FFmpegVideoConvertor',
            'preferedformat': 'mp4',
        }],
        'keepvideo': False,                           # No mantener archivos separados
    }
    
    # Añadir cookies del navegador si está habilitado (necesario para Instagram)
    if usar_cookies:
        ydl_opts['cookiesfrombrowser'] = (browser,)

    try:
        with yt_dlp.YoutubeDL(ydl_opts) as ydl:
            ydl.download([url])
        messagebox.showinfo("Descarga completa", "El video se ha descargado con éxito o se han ignorado errores.")
    except Exception as e:
        messagebox.showerror("Error de descarga", f"Se encontraron errores: {str(e)}")

# Crear la ventana principal
root = tk.Tk()
root.title("Descargador de Videos")
root.geometry("450x300")

# Etiqueta y campo de entrada para el enlace
tk.Label(root, text="Enlace (YouTube, Instagram, etc):").pack(pady=10)
url_entry = tk.Entry(root, width=50)
url_entry.pack(pady=5)

# Frame para opciones de cookies
cookies_frame = tk.LabelFrame(root, text="Autenticación (para Instagram/contenido privado)", padx=10, pady=5)
cookies_frame.pack(pady=10, padx=10, fill="x")

# Checkbox para usar cookies
usar_cookies_var = tk.BooleanVar(value=True)
tk.Checkbutton(cookies_frame, text="Usar cookies del navegador", variable=usar_cookies_var).pack(anchor="w")

# Selector de navegador
browser_frame = tk.Frame(cookies_frame)
browser_frame.pack(fill="x", pady=5)
tk.Label(browser_frame, text="Navegador:").pack(side="left")
browser_var = tk.StringVar(value='chrome')
browser_combo = ttk.Combobox(browser_frame, textvariable=browser_var, values=BROWSERS, state="readonly", width=15)
browser_combo.pack(side="left", padx=10)

# Botón para iniciar la descarga
tk.Button(root, text="Descargar Video", command=descargar_video, bg="#4CAF50", fg="white", padx=20, pady=5).pack(pady=20)

root.mainloop()