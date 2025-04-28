import os
import shutil
import tkinter as tk
from tkinter import filedialog, messagebox

def seleccionar_directorio_backup():
    global backup_dir
    backup_dir = filedialog.askdirectory(title="Seleccionar Directorio para el Backup")
    if backup_dir:
        dir_entry.delete(0, tk.END)
        dir_entry.insert(0, backup_dir)

def realizar_backup():
    if not backup_dir:
        messagebox.showerror("Error", "Por favor selecciona un directorio para el backup.")
        return

    try:
        # Crear directorios de backup
        os.makedirs(os.path.join(backup_dir, "etc"), exist_ok=True)
        os.makedirs(os.path.join(backup_dir, "home"), exist_ok=True)
        os.makedirs(os.path.join(backup_dir, "autostart"), exist_ok=True)

        # Backup de /etc/fstab
        fstab_src = "/etc/fstab"
        fstab_dst = os.path.join(backup_dir, "etc", "fstab")
        if os.path.exists(fstab_src):
            shutil.copy(fstab_src, fstab_dst)

        # Backup de /etc/sudoers
        sudoers_src = "/etc/sudoers"
        sudoers_dst = os.path.join(backup_dir, "etc", "sudoers")
        if os.path.exists(sudoers_src):
            shutil.copy(sudoers_src, sudoers_dst)

        # Backup de inicio automático (~/.config/autostart)
        autostart_src = os.path.expanduser("~/.config/autostart")
        autostart_dst = os.path.join(backup_dir, "autostart")
        if os.path.exists(autostart_src):
            shutil.copytree(autostart_src, autostart_dst, dirs_exist_ok=True)

        # Backup del directorio personal
        home_src = os.path.expanduser("~")
        home_dst = os.path.join(backup_dir, "home")
        shutil.copytree(home_src, home_dst, dirs_exist_ok=True, ignore=shutil.ignore_patterns("*.cache", "*.tmp"))

        # Crear script para reinstalar navegadores y VSCode
        generar_script_reinstalacion()

        messagebox.showinfo("Éxito", f"Backup realizado correctamente en:\n{backup_dir}")
    except Exception as e:
        messagebox.showerror("Error", f"Ocurrió un error durante el backup:\n{str(e)}")

def generar_script_reinstalacion():
    script_path = os.path.join(backup_dir, "reinstalar_navegadores_y_vscode.sh")
    navegadores = ["google-chrome", "firefox", "brave-browser"]
    vscode = "code"

    try:
        with open(script_path, "w") as script:
            script.write("#!/bin/bash\n\n")
            script.write("echo 'Instalando navegadores y Visual Studio Code...'\n")
            for navegador in navegadores:
                script.write(f"sudo apt-get install -y {navegador}\n")
            script.write(f"sudo apt-get install -y {vscode}\n")
            script.write("echo 'Instalación completada.'\n")
        os.chmod(script_path, 0o755)  # Hacer el script ejecutable
    except Exception as e:
        messagebox.showerror("Error", f"No se pudo crear el script de reinstalación:\n{str(e)}")

# GUI
root = tk.Tk()
root.title("Backup de Linux")

backup_dir = None

tk.Label(root, text="Directorio de Backup:").grid(row=0, column=0, padx=5, pady=5, sticky="e")
dir_entry = tk.Entry(root, width=50)
dir_entry.grid(row=0, column=1, padx=5, pady=5)
tk.Button(root, text="Seleccionar", command=seleccionar_directorio_backup).grid(row=0, column=2, padx=5, pady=5)

tk.Button(root, text="Realizar Backup", command=realizar_backup, bg="green", fg="white", width=20).grid(row=1, column=0, columnspan=3, pady=10)

root.mainloop()