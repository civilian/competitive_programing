import os
from tkinter import Tk, filedialog, Button, Label, StringVar, OptionMenu
from moviepy.video.io.VideoFileClip import VideoFileClip

# Función para seleccionar un archivo
def select_file():
    global file_path
    file_path = filedialog.askopenfilename(
        filetypes=[("Video Files", "*.mp4;*.mkv;*.avi;*.mov;*.flv")]
    )
    if file_path:
        input_label.config(text=f"Selected: {os.path.basename(file_path)}")

# Función para convertir el archivo
def convert_file():
    if not file_path:
        status_label.config(text="Please select a video file first.", fg="red")
        return

    output_format = format_var.get()
    if not output_format:
        status_label.config(text="Please select an output format.", fg="red")
        return

    try:
        video = VideoFileClip(file_path)
        output_file = os.path.splitext(file_path)[0] + f".{output_format}"
        video.write_videofile(output_file, codec="libx264")
        status_label.config(text=f"Conversion successful: {output_file}", fg="green")
    except Exception as e:
        status_label.config(text=f"Error: {e}", fg="red")

# Configuración de la ventana principal
root = Tk()
root.title("Video Converter")
root.geometry("400x250")

file_path = None

# Etiqueta de entrada
input_label = Label(root, text="No file selected.", wraplength=300)
input_label.pack(pady=10)

# Botón para seleccionar archivo
select_button = Button(root, text="Select Video File", command=select_file)
select_button.pack(pady=5)

# Menú desplegable para formatos de salida
format_var = StringVar(root)
format_var.set("")  # Valor inicial vacío
formats = ["mp4", "mkv", "avi", "mov", "flv"]
format_menu = OptionMenu(root, format_var, *formats)
format_menu.pack(pady=10)

# Botón para convertir archivo
convert_button = Button(root, text="Convert Video", command=convert_file)
convert_button.pack(pady=5)

# Etiqueta de estado
status_label = Label(root, text="", wraplength=300)
status_label.pack(pady=10)

# Ejecutar la aplicación
root.mainloop()
