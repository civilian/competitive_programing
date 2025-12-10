import whisper
import sys
import os

def transcribir_audio(ruta_audio):
    if not os.path.exists(ruta_audio):
        print(f"❌ No se encontró el archivo: {ruta_audio}")
        return

    print("🔍 Cargando modelo Whisper (esto puede tardar unos segundos)...")
    modelo = whisper.load_model("base")  # Puedes usar "tiny", "base", "small", "medium", "large"

    print(f"🎧 Transcribiendo: {ruta_audio}")
    resultado = modelo.transcribe(ruta_audio, fp16=False)

    texto = resultado["text"]
    print("\n📝 Transcripción completa:\n")
    print(texto)

    # Guarda en archivo de texto
    salida = os.path.splitext(ruta_audio)[0] + "_transcripcion.txt"
    with open(salida, "w", encoding="utf-8") as f:
        f.write(texto)

    print(f"\n✅ Transcripción guardada en: {salida}")

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Uso: python transcribir_audio.py archivo.m4a")
    else:
        transcribir_audio(sys.argv[1])
