import yt_dlp
import tkinter as tk
from tkinter import filedialog, messagebox
import os
import subprocess

# Función para descargar el video con audio
def descargar_video():
    # Obtener el enlace de YouTube ingresado
    url = url_entry.get()
    if not url:
        messagebox.showerror("Error", "Por favor ingresa un enlace de YouTube.")
        return

    # Seleccionar el directorio de salida
    output_dir = filedialog.askdirectory(title="Seleccionar directorio de salida")
    if not output_dir:
        messagebox.showerror("Error", "Por favor selecciona un directorio de salida.")
        return

    # Opciones para descargar video y audio por separado
    video_opts = {
        'format': 'bestvideo',  # Descargar solo el mejor video
        'outtmpl': f'{output_dir}/%(title)s_video.%(ext)s',  # Nombre del archivo de video
        'ignoreerrors': True
    }
    audio_opts = {
        'format': 'bestaudio',  # Descargar solo el mejor audio
        'outtmpl': f'{output_dir}/%(title)s_audio.%(ext)s',  # Nombre del archivo de audio
        'ignoreerrors': True
    }

    try:
        # Descargar el video
        with yt_dlp.YoutubeDL(video_opts) as ydl:
            video_info = ydl.extract_info(url, download=True)
            video_title = video_info.get('title', 'output')
            video_file = os.path.join(output_dir, f"{video_title}_video.mp4")

        # Descargar el audio
        with yt_dlp.YoutubeDL(audio_opts) as ydl:
            audio_info = ydl.extract_info(url, download=True)
            audio_file = os.path.join(output_dir, f"{video_title}_audio.m4a")

        # Verificar si ambos archivos existen
        if os.path.exists(video_file) and os.path.exists(audio_file):
            final_output = os.path.join(output_dir, f"{video_title}_final.mp4")
            # Combinar video y audio usando ffmpeg
            command = [
                "ffmpeg", "-i", video_file, "-i", audio_file, "-c:v", "copy", "-c:a", "aac", "-strict", "experimental", final_output
            ]
            subprocess.run(command, check=True)

            # Eliminar archivos temporales
            os.remove(video_file)
            os.remove(audio_file)

            messagebox.showinfo("Descarga completa", f"El video con audio se ha descargado y combinado con éxito: {final_output}")
        else:
            messagebox.showerror("Error de descarga", "No se pudieron descargar ambos archivos (video y audio).")
    except Exception as e:
        messagebox.showerror("Error de descarga", f"Se encontraron errores durante la descarga o combinación: {str(e)}")

# Crear la ventana principal
root = tk.Tk()
root.title("Descargador de YouTube")
root.geometry("400x200")

# Etiqueta y campo de entrada para el enlace de YouTube
tk.Label(root, text="Enlace de YouTube:").pack(pady=10)
url_entry = tk.Entry(root, width=50)
url_entry.pack(pady=5)

# Botón para iniciar la descarga
tk.Button(root, text="Descargar Video", command=descargar_video).pack(pady=20)

root.mainloop()