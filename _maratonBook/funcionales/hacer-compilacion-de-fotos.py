import tkinter as tk
from tkinter import filedialog, messagebox
from moviepy.editor import ImageClip, concatenate_videoclips
import os

def seleccionar_directorio():
    global directorio_fotos
    directorio_fotos = filedialog.askdirectory(title="Seleccionar Directorio de Fotos")
    if directorio_fotos:
        dir_entry.delete(0, tk.END)
        dir_entry.insert(0, directorio_fotos)

def guardar_video():
    global output_path
    output_path = filedialog.asksaveasfilename(defaultextension=".mp4", filetypes=[("MP4 files", "*.mp4")])
    if output_path:
        output_entry.delete(0, tk.END)
        output_entry.insert(0, output_path)

def crear_video():
    if not directorio_fotos or not output_path:
        messagebox.showerror("Error", "Por favor selecciona un directorio y un archivo de salida.")
        return

    try:
        # Obtener todas las imágenes del directorio
        imagenes = sorted([os.path.join(directorio_fotos, f) for f in os.listdir(directorio_fotos) if f.lower().endswith(('.png', '.jpg', '.jpeg'))])
        if not imagenes:
            messagebox.showerror("Error", "El directorio no contiene imágenes válidas.")
            return

        # Resolución deseada para todas las imágenes
        resolucion = (1920, 1080)  # Resolución Full HD

        # Crear clips de cada imagen con una duración de 3 segundos
        clips = []
        for img in imagenes:
            clip = ImageClip(img, duration=3)  # Duración de 3 segundos por imagen
            clip = clip.resize(height=resolucion[1])  # Escalar manteniendo el ratio (ajustar por altura)
            clips.append(clip.crossfadein(1))  # Transición suave de 1 segundo

        # Concatenar los clips con transiciones suaves
        video = concatenate_videoclips(clips, method="compose")

        # Guardar el video
        video.write_videofile(output_path, fps=24, codec="libx264", audio_codec="aac")
        messagebox.showinfo("Éxito", f"Video creado correctamente en:\n{output_path}")
    except Exception as e:
        messagebox.showerror("Error", f"Ocurrió un error:\n{str(e)}")

# GUI
root = tk.Tk()
root.title("Crear Video desde Fotos")

directorio_fotos = None
output_path = None

tk.Label(root, text="Directorio de Fotos:").grid(row=0, column=0, padx=5, pady=5, sticky="e")
dir_entry = tk.Entry(root, width=50)
dir_entry.grid(row=0, column=1, padx=5, pady=5)
tk.Button(root, text="Seleccionar", command=seleccionar_directorio).grid(row=0, column=2, padx=5, pady=5)

tk.Label(root, text="Guardar como:").grid(row=1, column=0, padx=5, pady=5, sticky="e")
output_entry = tk.Entry(root, width=50)
output_entry.grid(row=1, column=1, padx=5, pady=5)
tk.Button(root, text="Guardar", command=guardar_video).grid(row=1, column=2, padx=5, pady=5)

tk.Button(root, text="Crear Video", command=crear_video, bg="green", fg="white", width=20).grid(row=2, column=0, columnspan=3, pady=10)

root.mainloop()