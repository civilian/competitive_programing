import tkinter as tk
from tkinter import filedialog, messagebox
from moviepy.editor import VideoFileClip

def dividir_video():
    # Obtener el archivo de video seleccionado
    video_path = filedialog.askopenfilename(title="Selecciona un video", filetypes=[("Video files", "*.mp4 *.avi *.mov *.mkv")])
    if not video_path:
        return

    # Cargar el video
    try:
        video = VideoFileClip(video_path)
    except Exception as e:
        messagebox.showerror("Error", f"No se pudo cargar el video: {e}")
        return

    # Obtener la duración y calcular el punto medio
    duracion = video.duration
    punto_medio = duracion / 2

    # Seleccionar los archivos de salida
    primera_mitad_path = filedialog.asksaveasfilename(defaultextension=".mp4", title="Guardar primera mitad como", filetypes=[("MP4 files", "*.mp4")])
    segunda_mitad_path = filedialog.asksaveasfilename(defaultextension=".mp4", title="Guardar segunda mitad como", filetypes=[("MP4 files", "*.mp4")])

    if not primera_mitad_path or not segunda_mitad_path:
        messagebox.showwarning("Advertencia", "Debes seleccionar ambas rutas de salida.")
        return

    # Dividir y guardar las mitades
    try:
        primera_mitad = video.subclip(0, punto_medio)
        segunda_mitad = video.subclip(punto_medio, duracion)

        primera_mitad.write_videofile(primera_mitad_path, codec="libx264")
        segunda_mitad.write_videofile(segunda_mitad_path, codec="libx264")

        messagebox.showinfo("Éxito", "Video dividido exitosamente en dos mitades.")
    except Exception as e:
        messagebox.showerror("Error", f"No se pudo dividir el video: {e}")
    finally:
        video.close()

# Crear la interfaz de usuario
root = tk.Tk()
root.title("Dividir Video en Dos Mitades")
root.geometry("300x150")

label = tk.Label(root, text="Dividir un video en dos mitades", font=("Arial", 14))
label.pack(pady=10)

boton_dividir = tk.Button(root, text="Seleccionar video y dividir", command=dividir_video, font=("Arial", 12))
boton_dividir.pack(pady=10)

root.mainloop()
