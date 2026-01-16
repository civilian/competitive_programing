import os
from tkinter import Tk, filedialog, Listbox, Button, END, messagebox
from moviepy import VideoFileClip, concatenate_videoclips


# Lista para almacenar las rutas completas de los videos
rutas_videos = []


def unir_videos_en_orden(rutas_ordenadas, salida="video_unido.mp4"):
    """Une los videos en el orden especificado por sus rutas completas."""
    clips = []
    try:
        for ruta in rutas_ordenadas:
            if os.path.exists(ruta):
                clips.append(VideoFileClip(ruta))
            else:
                messagebox.showerror("Error", f"El archivo no existe: {ruta}")
                return

        if not clips:
            messagebox.showerror("Error", "No hay videos válidos para unir.")
            return

        # Unir los videos en el orden especificado
        video_final = concatenate_videoclips(clips, method="compose")
        
        # Pedir al usuario dónde guardar el video
        archivo_salida = filedialog.asksaveasfilename(
            defaultextension=".mp4",
            filetypes=[("MP4 files", "*.mp4"), ("All files", "*.*")],
            title="Guardar video unido como..."
        )
        
        if archivo_salida:
            video_final.write_videofile(archivo_salida, codec="libx264")
            
            # Cerrar los clips para liberar recursos
            for clip in clips:
                clip.close()
            video_final.close()
            
            messagebox.showinfo("Éxito", f"Videos unidos exitosamente en:\n{archivo_salida}")
        else:
            # Cerrar los clips si el usuario canceló
            for clip in clips:
                clip.close()
            
    except Exception as e:
        messagebox.showerror("Error", f"Error al unir los videos:\n{str(e)}")
        # Cerrar los clips en caso de error
        for clip in clips:
            try:
                clip.close()
            except:
                pass


# Crear ventana principal de la interfaz gráfica
root = Tk()
root.title("Unir Videos - Selección Individual")
root.geometry("500x450")


# Función para agregar videos a la lista
def agregar_videos():
    """Permite seleccionar uno o más archivos de video."""
    archivos = filedialog.askopenfilenames(
        title="Selecciona uno o más videos",
        filetypes=[
            ("Archivos de video", "*.mp4 *.mkv *.avi *.webm *.mov *.flv"),
            ("MP4 files", "*.mp4"),
            ("MKV files", "*.mkv"),
            ("AVI files", "*.avi"),
            ("WebM files", "*.webm"),
            ("Todos los archivos", "*.*")
        ]
    )
    
    if archivos:
        for archivo in archivos:
            if archivo not in rutas_videos:
                rutas_videos.append(archivo)
                # Mostrar solo el nombre del archivo en la lista
                nombre_archivo = os.path.basename(archivo)
                listbox_videos.insert(END, nombre_archivo)
            else:
                messagebox.showwarning("Advertencia", f"El video ya está en la lista:\n{os.path.basename(archivo)}")


# Función para eliminar el video seleccionado de la lista
def eliminar_video():
    """Elimina el video seleccionado de la lista."""
    seleccion = listbox_videos.curselection()
    if seleccion:
        index = seleccion[0]
        rutas_videos.pop(index)
        listbox_videos.delete(index)
    else:
        messagebox.showwarning("Advertencia", "Selecciona un video para eliminar.")


# Función para limpiar toda la lista
def limpiar_lista():
    """Elimina todos los videos de la lista."""
    if rutas_videos:
        respuesta = messagebox.askyesno("Confirmar", "¿Deseas eliminar todos los videos de la lista?")
        if respuesta:
            rutas_videos.clear()
            listbox_videos.delete(0, END)


# Función para mover un archivo hacia arriba en la lista
def mover_arriba():
    seleccion = listbox_videos.curselection()
    if seleccion:
        index = seleccion[0]
        if index > 0:
            # Mover en la lista de rutas
            rutas_videos[index], rutas_videos[index - 1] = rutas_videos[index - 1], rutas_videos[index]
            # Mover en el listbox
            nombre = listbox_videos.get(index)
            listbox_videos.delete(index)
            listbox_videos.insert(index - 1, nombre)
            listbox_videos.select_set(index - 1)
    else:
        messagebox.showwarning("Advertencia", "Selecciona un video para mover.")


# Función para mover un archivo hacia abajo en la lista
def mover_abajo():
    seleccion = listbox_videos.curselection()
    if seleccion:
        index = seleccion[0]
        if index < len(rutas_videos) - 1:
            # Mover en la lista de rutas
            rutas_videos[index], rutas_videos[index + 1] = rutas_videos[index + 1], rutas_videos[index]
            # Mover en el listbox
            nombre = listbox_videos.get(index)
            listbox_videos.delete(index)
            listbox_videos.insert(index + 1, nombre)
            listbox_videos.select_set(index + 1)
    else:
        messagebox.showwarning("Advertencia", "Selecciona un video para mover.")


# Función para iniciar el proceso de unión de videos en el orden seleccionado
def unir_videos():
    if not rutas_videos:
        messagebox.showerror("Error", "No hay videos en la lista. Agrega al menos un video.")
        return
    
    if len(rutas_videos) < 2:
        messagebox.showerror("Error", "Necesitas al menos 2 videos para unir.")
        return
    
    respuesta = messagebox.askyesno(
        "Confirmar", 
        f"¿Deseas unir {len(rutas_videos)} videos en el orden mostrado?"
    )
    
    if respuesta:
        unir_videos_en_orden(rutas_videos.copy())


# Botones y Listbox
listbox_videos = Listbox(root, selectmode="single", width=60, height=12)
listbox_videos.pack(pady=10)

# Frame para los botones de control
from tkinter import Frame
frame_botones = Frame(root)
frame_botones.pack(pady=5)

boton_agregar = Button(frame_botones, text="Agregar Videos", command=agregar_videos, width=15)
boton_agregar.pack(side="left", padx=5)

boton_eliminar = Button(frame_botones, text="Eliminar", command=eliminar_video, width=15)
boton_eliminar.pack(side="left", padx=5)

boton_limpiar = Button(frame_botones, text="Limpiar Lista", command=limpiar_lista, width=15)
boton_limpiar.pack(side="left", padx=5)

# Frame para los botones de ordenamiento
frame_orden = Frame(root)
frame_orden.pack(pady=5)

boton_arriba = Button(frame_orden, text="Mover Arriba", command=mover_arriba, width=15)
boton_arriba.pack(side="left", padx=5)

boton_abajo = Button(frame_orden, text="Mover Abajo", command=mover_abajo, width=15)
boton_abajo.pack(side="left", padx=5)

# Botón principal para unir
boton_unir = Button(root, text="Unir Videos en Orden", command=unir_videos, bg="#4CAF50", fg="white", font=("Arial", 10, "bold"), height=2)
boton_unir.pack(pady=20)

root.mainloop()
