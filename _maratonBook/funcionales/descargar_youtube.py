import yt_dlp
import tkinter as tk
from tkinter import filedialog, messagebox

# Función para descargar el video
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

    ydl_opts = {
        'format': 'bestvideo+bestaudio/best',         # Mejor calidad de video y audio
        'outtmpl': f'{output_dir}/%(title)s.%(ext)s', # Directorio y nombre del archivo
        'merge_output_format': 'mp4',                # Formato final en MP4
        'ignoreerrors': True                         # Ignorar errores durante la descarga
    }

    try:
        with yt_dlp.YoutubeDL(ydl_opts) as ydl:
            ydl.download([url])
        messagebox.showinfo("Descarga completa", "El video se ha descargado con éxito o se han ignorado errores.")
    except Exception as e:
        messagebox.showerror("Error de descarga", f"Se encontraron errores, pero se continuó: {str(e)}")

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

´+¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿