import tkinter as tk
from tkinter import filedialog, messagebox
from PIL import Image, ImageTk
import os

# Variable global para recordar el último directorio
last_directory = os.getcwd()  # Inicialmente, el directorio actual

def open_image():
    global last_directory
    try:
        file_path = filedialog.askopenfilename(
            initialdir=last_directory,
            filetypes=[("Image files", "*")],
            title="Seleccionar imagen"
        )
        if file_path:
            last_directory = os.path.dirname(file_path)  # Actualizar el último directorio
            global img
            img = Image.open(file_path)
            img.thumbnail((300, 300))  # Resize for display
            img_tk = ImageTk.PhotoImage(img)
            label_img.config(image=img_tk)
            label_img.image = img_tk
    except Exception as e:
        messagebox.showerror("Error", f"No se pudo abrir la imagen: {str(e)}")

def make_square():
    if img:
        # Get the dimensions of the original image
        width, height = img.size
        
        # Determine the size of the square canvas
        square_size = max(width, height)
        
        # Create a new square image with a white background
        square_img = Image.new("RGB", (square_size, square_size), (255, 255, 255))
        
        # Calculate the position to center the original image on the square canvas
        x_offset = (square_size - width) // 2
        y_offset = (square_size - height) // 2
        
        # Paste the original image onto the square canvas
        square_img.paste(img, (x_offset, y_offset))
        
        # Display the result
        square_img.thumbnail((300, 300))  # Resize for display
        square_img_tk = ImageTk.PhotoImage(square_img)
        label_result.config(image=square_img_tk)
        label_result.image = square_img_tk

        # Save the new image globally for saving later
        global final_image
        final_image = square_img
    else:
        messagebox.showerror("Error", "Por favor, selecciona una imagen primero.")

def save_image():
    if final_image:
        file_path = filedialog.asksaveasfilename(
            initialdir=last_directory,
            defaultextension=".png",
            filetypes=[("PNG files", "*.png"), ("JPEG files", "*.jpg"), ("All files", "*.*")],
            title="Guardar imagen"
        )
        if file_path:
            final_image.save(file_path)
            messagebox.showinfo("Guardado", "La imagen cuadrada se ha guardado con éxito.")
    else:
        messagebox.showerror("Error", "No hay imagen para guardar.")

# Initialize main window
root = tk.Tk()
root.title("Convert to Square Image")

# Initialize global variables
img = None
final_image = None

# Create and place widgets
btn_open = tk.Button(root, text="Open Image", command=open_image)
btn_open.pack()

label_img = tk.Label(root)
label_img.pack()

btn_square = tk.Button(root, text="Make Square", command=make_square)
btn_square.pack()

label_result = tk.Label(root)
label_result.pack()

btn_save = tk.Button(root, text="Save Image", command=save_image)
btn_save.pack()

# Run the application
root.mainloop()