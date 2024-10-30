import yt_dlp

url = "https://youtu.be/zJSY8tbf_ys"

ydl_opts = {
    'format': 'bestaudio/best',
    'outtmpl': 'audio.%(ext)s',
}

with yt_dlp.YoutubeDL(ydl_opts) as ydl:
    ydl.download([url])

print("Descarga completa.")