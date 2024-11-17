import os
from tkinter import Tk, filedialog, Listbox, Button, END, messagebox, Label
from moviepy.editor import VideoFileClip, concatenate_videoclips

# Crear ventana principal de la interfaz gráfica
root = Tk()
root.title("Unir Videos de Uno en Uno")
root.geometry("500x450")

directorio = ""
directorio_salida = ""
video_base_path = ""


# Función para unir el video base con un video seleccionado
def unir_video(video_base_path, archivo, salida):
    clip_base = VideoFileClip(video_base_path)
    clip_extra = VideoFileClip(archivo)

    # Unir los videos
    video_final = concatenate_videoclips([clip_base, clip_extra], method="compose")
    video_final.write_videofile(salida, codec="libx264")

    #messagebox.showinfo("Éxito", f"Video unido guardado en {salida}")


# Función para seleccionar el video base
def seleccionar_video_base():
    global video_base_path
    video_base_path = filedialog.askopenfilename(
        title="Selecciona el video base",
        filetypes=[("Archivos de video", "*.mp4 *.mkv *.avi *.webm")]
    )
    if video_base_path:
        label_video_base.config(text=f"Video base seleccionado: {os.path.basename(video_base_path)}")
    else:
        messagebox.showerror("Error", "No se seleccionó ningún video base.")


# Función para seleccionar el directorio de salida
def seleccionar_directorio_salida():
    global directorio_salida
    directorio_salida = filedialog.askdirectory(title="Selecciona el directorio de salida")
    if directorio_salida:
        label_directorio.config(text=f"Directorio de salida: {directorio_salida}")
    else:
        messagebox.showerror("Error", "No se seleccionó ningún directorio.")


# Función para seleccionar el directorio con los videos
def seleccionar_directorio():
    global directorio
    directorio = filedialog.askdirectory(title="Selecciona el directorio con los videos")

    if directorio:
        archivos_video = [f for f in os.listdir(directorio) if f.endswith((".mp4", ".mkv", ".avi", ".webm"))]
        listbox_videos.delete(0, END)
        for archivo in archivos_video:
            listbox_videos.insert(END, archivo)
    else:
        messagebox.showerror("Error", "No se seleccionó ningún directorio.")


# Función para unir el video base con cada archivo seleccionado
def unir_videos():
    if not video_base_path:
        messagebox.showerror("Error", "No se ha seleccionado un video base.")
        return
    if not directorio_salida:
        messagebox.showerror("Error", "No se ha seleccionado un directorio de salida.")
        return
    if not directorio:
        messagebox.showerror("Error", "No se ha seleccionado un directorio de videos.")
        return

    archivos_ordenados = listbox_videos.get(0, END)
    if archivos_ordenados:
        for archivo in archivos_ordenados:
            ruta_archivo = os.path.join(directorio, archivo)
            salida = os.path.join(directorio_salida, f"unido_{archivo}")
            unir_video(video_base_path, ruta_archivo, salida)
    else:
        messagebox.showerror("Error", "No hay videos en la lista.")


# Widgets de la interfaz gráfica
label_video_base = Label(root, text="Video base no seleccionado", wraplength=400)
label_video_base.pack(pady=5)

boton_video_base = Button(root, text="Seleccionar Video Base", command=seleccionar_video_base)
boton_video_base.pack(pady=5)

label_directorio = Label(root, text="Directorio de salida no seleccionado", wraplength=400)
label_directorio.pack(pady=5)

boton_directorio_salida = Button(root, text="Seleccionar Directorio de Salida", command=seleccionar_directorio_salida)
boton_directorio_salida.pack(pady=5)

listbox_videos = Listbox(root, selectmode="single", width=50, height=10)
listbox_videos.pack(pady=10)

boton_directorio = Button(root, text="Seleccionar Directorio de Videos", command=seleccionar_directorio)
boton_directorio.pack(pady=5)

boton_unir = Button(root, text="Unir Videos Uno por Uno", command=unir_videos)
boton_unir.pack(pady=20)

root.mainloop()
