import os
from moviepy.editor import AudioFileClip
from tkinter import Tk, filedialog, messagebox


def convertir_webm_a_mp3(directorio):
    # Obtener todos los archivos .webm en el directorio
    archivos_webm = [f for f in os.listdir(directorio) if f.endswith('.webm')]

    if not archivos_webm:
        messagebox.showinfo("Info", "No se encontraron archivos .webm en el directorio.")
        return

    for archivo in archivos_webm:
        ruta_entrada = os.path.join(directorio, archivo)
        ruta_salida = os.path.join(directorio, archivo.replace('.webm', '.mp3'))

        try:
            # Cargar el archivo .webm y extraer el audio como .mp3
            audio = AudioFileClip(ruta_entrada)
            audio.write_audiofile(ruta_salida, codec="mp3")
            audio.close()
            print(f"{archivo} convertido a mp3.")
        except Exception as e:
            print(f"Error al convertir {archivo}: {e}")

    messagebox.showinfo("Éxito", "Todos los archivos .webm fueron convertidos a .mp3.")


# Crear la interfaz de usuario
root = Tk()
root.withdraw()  # Ocultar la ventana principal

directorio = filedialog.askdirectory(title="Selecciona el directorio con archivos .webm")
if directorio:
    convertir_webm_a_mp3(directorio)
else:
    print("No se seleccionó ningún directorio.")
