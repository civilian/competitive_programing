import tkinter as tk
from tkinter import filedialog, messagebox
from moviepy.editor import VideoFileClip, AudioFileClip

def unir_audio_video(video_path, audio_path, output_path):
    video = VideoFileClip(video_path)
    audio = AudioFileClip(audio_path)
    video = video.set_audio(audio)
    video.write_videofile(output_path, codec='libx264', audio_codec='aac')

def seleccionar_video():
    file_path = filedialog.askopenfilename(title="Seleccionar Video", filetypes=[("Video Files", "*.mp4 *.avi *.mov")])
    video_entry.delete(0, tk.END)
    video_entry.insert(0, file_path)

def seleccionar_audio():
    file_path = filedialog.askopenfilename(title="Seleccionar Audio", filetypes=[("Audio Files", "*.mp3 *.wav *.m4a *.mp4")])
    audio_entry.delete(0, tk.END)
    audio_entry.insert(0, file_path)

def guardar_video():
    file_path = filedialog.asksaveasfilename(defaultextension=".mp4", filetypes=[("MP4 files", "*.mp4")])
    output_entry.delete(0, tk.END)
    output_entry.insert(0, file_path)

def procesar():
    video_path = video_entry.get()
    audio_path = audio_entry.get()
    output_path = output_entry.get()

    if not video_path or not audio_path or not output_path:
        messagebox.showerror("Error", "Todos los campos son obligatorios.")
        return

    try:
        unir_audio_video(video_path, audio_path, output_path)
        messagebox.showinfo("Éxito", f"Archivo creado correctamente en:\n{output_path}")
    except Exception as e:
        messagebox.showerror("Error", f"Ocurrió un error:\n{str(e)}")

# GUI
root = tk.Tk()
root.title("Unir Audio y Video")

tk.Label(root, text="Video:").grid(row=0, column=0, padx=5, pady=5, sticky="e")
video_entry = tk.Entry(root, width=50)
video_entry.grid(row=0, column=1, padx=5, pady=5)
tk.Button(root, text="Seleccionar", command=seleccionar_video).grid(row=0, column=2, padx=5, pady=5)

tk.Label(root, text="Audio:").grid(row=1, column=0, padx=5, pady=5, sticky="e")
audio_entry = tk.Entry(root, width=50)
audio_entry.grid(row=1, column=1, padx=5, pady=5)
tk.Button(root, text="Seleccionar", command=seleccionar_audio).grid(row=1, column=2, padx=5, pady=5)

tk.Label(root, text="Guardar como:").grid(row=2, column=0, padx=5, pady=5, sticky="e")
output_entry = tk.Entry(root, width=50)
output_entry.grid(row=2, column=1, padx=5, pady=5)
tk.Button(root, text="Guardar", command=guardar_video).grid(row=2, column=2, padx=5, pady=5)

tk.Button(root, text="Unir", command=procesar, bg="green", fg="white", width=20).grid(row=3, column=0, columnspan=3, pady=10)

root.mainloop()
