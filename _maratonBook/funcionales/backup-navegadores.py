import os
import subprocess
import tkinter as tk
from tkinter import filedialog, messagebox

def detectar_navegadores():
    """
    Detecta navegadores instalados en el sistema.
    """
    navegadores = []
    comandos_navegadores = {
        "google-chrome": "google-chrome",
        "firefox": "firefox",
        "brave-browser": "brave-browser",
        "chromium": "chromium",
    }

    for nombre, comando in comandos_navegadores.items():
        if subprocess.call(f"command -v {comando}", shell=True, stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL) == 0:
            navegadores.append(nombre)

    return navegadores

def generar_script_reinstalacion(navegadores, vscode=True):
    """
    Genera un script Bash para reinstalar navegadores y Visual Studio Code.
    """
    # Preguntar dónde guardar el script
    script_path = filedialog.asksaveasfilename(
        defaultextension=".sh",
        filetypes=[("Shell Script", "*.sh")],
        title="Guardar Script de Reinstalación"
    )

    if not script_path:
        messagebox.showerror("Error", "No se seleccionó un archivo para guardar el script.")
        return

    try:
        with open(script_path, "w") as script:
            script.write("#!/bin/bash\n\n")
            script.write("echo 'Instalando navegadores y Visual Studio Code...'\n\n")

            # Agregar comandos para instalar navegadores
            for navegador in navegadores:
                script.write(f"sudo apt-get install -y {navegador}\n")

            # Agregar comando para instalar Visual Studio Code
            if vscode:
                script.write("sudo apt-get install -y code\n")

            script.write("\necho 'Instalación completada.'\n")

        # Hacer el script ejecutable
        os.chmod(script_path, 0o755)
        messagebox.showinfo("Éxito", f"Script generado exitosamente:\n{script_path}")
    except Exception as e:
        messagebox.showerror("Error", f"No se pudo generar el script:\n{str(e)}")

def main():
    """
    Función principal para la GUI.
    """
    print("Detectando navegadores instalados...")
    navegadores = detectar_navegadores()

    if not navegadores:
        messagebox.showerror("Error", "No se detectaron navegadores instalados.")
        return

    print(f"Navegadores detectados: {', '.join(navegadores)}")
    generar_script_reinstalacion(navegadores)

# GUI
root = tk.Tk()
root.title("Generar Script de Reinstalación")

tk.Label(root, text="Generar un script para reinstalar navegadores y Visual Studio Code").pack(pady=10)
tk.Button(root, text="Generar Script", command=main, bg="green", fg="white", width=20).pack(pady=10)

root.mainloop()