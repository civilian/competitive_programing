import yt_dlp

url = "http://www.youtube.com/watch?v=EHnlNCODekc"

ydl_opts = {
    'format': 'bestvideo+bestaudio/best',  # Selecciona el mejor formato de video y audio combinados
    'outtmpl': '%(title)s.%(ext)s',            # Nombre del archivo de salida
    'merge_output_format': 'mp4'           # Formato final del archivo combinado
}

with yt_dlp.YoutubeDL(ydl_opts) as ydl:
    ydl.download([url])

print("Descarga completa.")
