import pyautogui
import time
import tkinter as tk
from tkinter import messagebox


def start_autotyper():
    try:
        # Obtener valores de los campos de entrada
        texto = texto_entry.get()
        intervalo = float(intervalo_entry.get()) if intervalo_entry.get() else 0
        tiempo_espera = float(tiempo_espera_entry.get()) if tiempo_espera_entry.get() else 3
        repeticiones = int(repeticiones_entry.get()) if repeticiones_entry.get() else 1

        if not texto:
            messagebox.showerror("Error", "Por favor, ingresa el texto para escribir.")
            return

        # Mostrar mensaje de aviso y esperar el tiempo especificado
        messagebox.showinfo("Autotyper",
                            f"Comenzando en {tiempo_espera} segundos. Coloca el cursor donde quieras que se escriba el texto.")
        time.sleep(tiempo_espera)

        # Comienza el proceso de escritura automática
        for _ in range(repeticiones):
            pyautogui.write(texto, interval=intervalo)
            pyautogui.press("enter")  # Opcional: agrega un Enter al final de cada repetición

        messagebox.showinfo("Autotyper", "Autotyper finalizado.")
    except ValueError:
        messagebox.showerror("Error", "Por favor, ingresa valores numéricos válidos para los tiempos y repeticiones.")


# Crear la interfaz de usuario
root = tk.Tk()
root.title("Autotyper")

# Etiquetas y campos de entrada
tk.Label(root, text="Texto a escribir:").grid(row=0, column=0, padx=10, pady=5)
texto_entry = tk.Entry(root, width=30)
texto_entry.grid(row=0, column=1, padx=10, pady=5)

tk.Label(root, text="Intervalo entre teclas (segundos, predeterminado 0):").grid(row=1, column=0, padx=10, pady=5)
intervalo_entry = tk.Entry(root, width=10)
intervalo_entry.insert(0, "0")  # Valor predeterminado de intervalo

tk.Label(root, text="Tiempo de espera antes de comenzar (segundos, predeterminado 3):").grid(row=2, column=0, padx=10,
                                                                                             pady=5)
tiempo_espera_entry = tk.Entry(root, width=10)
tiempo_espera_entry.insert(0, "3")  # Valor predeterminado de tiempo de espera

tk.Label(root, text="Repeticiones (opcional, predeterminado 1):").grid(row=3, column=0, padx=10, pady=5)
repeticiones_entry = tk.Entry(root, width=10)
repeticiones_entry.grid(row=3, column=1, padx=10, pady=5)

# Botón para iniciar el autotyper
start_button = tk.Button(root, text="Iniciar Autotyper", command=start_autotyper)
start_button.grid(row=4, column=0, columnspan=2, pady=10)

root.mainloop()
