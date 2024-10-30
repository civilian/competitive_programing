import os
import time
import mimetypes


def esperar_archivo_multimedia_en_directorio(directorio, intervalo=5):
    # Definir los tipos MIME aceptados para video y audio
    tipos_video_audio = [
        'video/mp4', 'video/x-m4v', 'video/x-msvideo', 'video/quicktime', 'video/x-matroska',
        'audio/mpeg', 'audio/mp4', 'audio/x-wav', 'audio/x-aac', 'audio/ogg', 'audio/x-m4a'
    ]

    print(f"Esperando un archivo de video o audio en el directorio: {directorio}...")

    while True:
        # Listar archivos en el directorio
        archivos = os.listdir(directorio)

        for archivo in archivos:
            # Obtener la ruta completa del archivo
            ruta_archivo = os.path.join(directorio, archivo)

            # Verificar si el archivo es un tipo de video o audio aceptado
            tipo_mime, _ = mimetypes.guess_type(ruta_archivo)
            if tipo_mime in tipos_video_audio:
                print(f"Archivo multimedia encontrado: {archivo}")
                return archivo

        # Esperar el intervalo de tiempo antes de volver a verificar
        time.sleep(intervalo)


def format_time(seconds):
    h = int(seconds // 3600)
    m = int((seconds % 3600) // 60)
    s = seconds % 60
    return f"{h:02}:{m:02}:{s:06.3f}".replace('.', ',')

import smtplib
import os
from email.message import EmailMessage
from email.utils import formataddr

def enviar_correo_con_adjuntos(remitente, destinatario, asunto, cuerpo, ruta_archivo, smtp_server, smtp_port, contrasena):
    # Crear el mensaje de correo
    msg = EmailMessage()
    msg['From'] = formataddr(('Nombre Remitente', remitente))
    msg['To'] = destinatario
    msg['Subject'] = asunto
    msg.set_content(cuerpo)

    # Adjuntar el archivo
    with open(ruta_archivo, 'rb') as f:
        nombre_archivo = os.path.basename(ruta_archivo)
        # Detectar el tipo MIME automáticamente
        msg.add_attachment(f.read(), maintype='application', subtype='octet-stream', filename=nombre_archivo)

    # Enviar el correo
    try:
        with smtplib.SMTP_SSL(smtp_server, smtp_port) as server:
            server.login(remitente, contrasena)
            server.send_message(msg)
        print("Correo enviado con éxito")
    except Exception as e:
        print(f"Error al enviar el correo: {e}")




import whisper

model = whisper.load_model("base")

video = esperar_archivo_multimedia_en_directorio('./')
nombre_subtitulos = video[:-4]+".srt"
print(nombre_subtitulos)

# Transcripción completa del archivo multimedia
result = model.transcribe(video, task="transcribe", condition_on_previous_text=False)

# Genera un archivo de subtítulos en formato SRT
with open(nombre_subtitulos, "w") as f:
    for i, segment in enumerate(result['segments']):
        # SRT requiere un índice, tiempo inicial y final, y el texto
        start = segment['start']
        end = segment['end']
        text = segment['text']
        f.write(f"{i + 1}\n")
        f.write(f"{format_time(start)} --> {format_time(end)}\n")
        f.write(f"{text}\n\n")

# Configuración y datos del correo
remitente = 'civilian.ganzuador@gmail.com'
destinatario = 'civilian.ganzuador@gmail.com'
asunto = nombre_subtitulos + " subtitulos subtitulo srt"
cuerpo = nombre_subtitulos
ruta_archivo = nombre_subtitulos  # Cambia por la ruta del archivo que deseas adjuntar
smtp_server = 'smtp.gmail.com'
smtp_port = 465
contrasena = 'tvet abht rrle ecrb'

# Enviar el correo
enviar_correo_con_adjuntos(remitente, destinatario, asunto, cuerpo, ruta_archivo, smtp_server, smtp_port, contrasena)