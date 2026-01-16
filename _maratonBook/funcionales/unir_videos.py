import os
from tkinter import Tk, filedialog, Listbox, Button, END, messagebox
from moviepy import VideoFileClip, concatenate_videoclips


def unir_videos_en_orden(directorio, archivos_ordenados, salida="video_unido.mp4"):
    clips = []
    for archivo in archivos_ordenados:
        ruta = os.path.join(directorio, archivo)
        clips.append(VideoFileClip(ruta))

    # Unir los videos en el orden especificado
    video_final = concatenate_videoclips(clips, method="compose")
    video_final.write_videofile(salida, codec="libx264")

    messagebox.showinfo("Éxito", f"Videos unidos exitosamente en {salida}")


# Crear ventana principal de la interfaz gráfica
root = Tk()
root.title("Unir Videos en Orden")
root.geometry("400x350")


# Función para seleccionar el directorio y cargar archivos
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


# Función para mover un archivo hacia arriba en la lista
def mover_arriba():
    seleccion = listbox_videos.curselection()
    if seleccion:
        index = seleccion[0]
        if index > 0:
            nombre = listbox_videos.get(index)
            listbox_videos.delete(index)
            listbox_videos.insert(index - 1, nombre)
            listbox_videos.select_set(index - 1)


# Función para mover un archivo hacia abajo en la lista
def mover_abajo():
    seleccion = listbox_videos.curselection()
    if seleccion:
        index = seleccion[0]
        if index < listbox_videos.size() - 1:
            nombre = listbox_videos.get(index)
            listbox_videos.delete(index)
            listbox_videos.insert(index + 1, nombre)
            listbox_videos.select_set(index + 1)


# Función para iniciar el proceso de unión de videos en el orden seleccionado
def unir_videos():
    if not directorio:
        messagebox.showerror("Error", "No se ha seleccionado un directorio.")
        return

    archivos_ordenados = listbox_videos.get(0, END)
    if archivos_ordenados:
        unir_videos_en_orden(directorio, archivos_ordenados)
    else:
        messagebox.showerror("Error", "No hay videos en la lista.")


# Botones y Listbox
listbox_videos = Listbox(root, selectmode="single", width=50, height=10)
listbox_videos.pack(pady=10)

boton_directorio = Button(root, text="Seleccionar Directorio", command=seleccionar_directorio)
boton_directorio.pack(pady=5)

boton_arriba = Button(root, text="Mover Arriba", command=mover_arriba)
boton_arriba.pack(pady=5)

boton_abajo = Button(root, text="Mover Abajo", command=mover_abajo)
boton_abajo.pack(pady=5)

boton_unir = Button(root, text="Unir Videos en Orden", command=unir_videos)
boton_unir.pack(pady=20)

root.mainloop()
