import tkinter as tk
from tkinter import filedialog, messagebox
from PIL import Image, ImageTk
import os

# Variable global para recordar el último directorio
last_directory = os.getcwd()  # Inicialmente, el directorio actual

def open_image1():
    global last_directory
    file_path = filedialog.askopenfilename(initialdir=last_directory)
    if file_path:
        last_directory = os.path.dirname(file_path)  # Actualizar el último directorio
        global img1
        img1 = Image.open(file_path)
        img1.thumbnail((300, 300))  # Resize for display
        img1_tk = ImageTk.PhotoImage(img1)
        label_img1.config(image=img1_tk)
        label_img1.image = img1_tk

def open_image2():
    global last_directory
    file_path = filedialog.askopenfilename(initialdir=last_directory)
    if file_path:
        last_directory = os.path.dirname(file_path)  # Actualizar el último directorio
        global img2
        img2 = Image.open(file_path)
        img2.thumbnail((300, 300))  # Resize for display
        img2_tk = ImageTk.PhotoImage(img2)
        label_img2.config(image=img2_tk)
        label_img2.image = img2_tk

def open_background_image():
    global last_directory
    file_path = filedialog.askopenfilename(initialdir=last_directory)
    if file_path:
        last_directory = os.path.dirname(file_path)  # Actualizar el último directorio
        global background_img
        background_img = Image.open(file_path)
        background_img.thumbnail((300, 300))  # Resize for display
        bg_img_tk = ImageTk.PhotoImage(background_img)
        label_background.config(image=bg_img_tk)
        label_background.image = bg_img_tk

def join_images_with_background():
    if img1 and img2 and background_img:
        # Ensure both images have the same height
        img1_resized = img1.resize((img1.width, max(img1.height, img2.height)))
        img2_resized = img2.resize((img2.width, max(img1.height, img2.height)))
        
        # Define the space width
        space_width = 200  # Sufficient space for a face in the center
        
        # Create a canvas with the size of the background image
        canvas_size = max(img1_resized.height, img1_resized.width + img2_resized.width + space_width)
        background_resized = background_img.resize((canvas_size, canvas_size))
        
        # Calculate positions to center the images horizontally and vertically
        img1_x = (canvas_size - (img1_resized.width + img2_resized.width + space_width)) // 2
        img1_y = (canvas_size - img1_resized.height) // 2
        img2_x = img1_x + img1_resized.width + space_width
        img2_y = img1_y
        
        # Paste images onto the background
        background_resized.paste(img1_resized, (img1_x, img1_y), mask=None)
        background_resized.paste(img2_resized, (img2_x, img2_y), mask=None)
        
        # Display the result
        background_resized.thumbnail((600, 600))  # Resize for display
        new_img_tk = ImageTk.PhotoImage(background_resized)
        label_result.config(image=new_img_tk)
        label_result.image = new_img_tk

        # Save the new image globally for saving later
        global final_image
        final_image = background_resized
    else:
        messagebox.showerror("Error", "Por favor, selecciona las dos imágenes y el fondo.")

def save_image():
    if final_image:
        file_path = filedialog.asksaveasfilename(defaultextension=".png", filetypes=[("PNG files", "*.png"), ("JPEG files", "*.jpg"), ("All files", "*.*")])
        if file_path:
            final_image.save(file_path)
            messagebox.showinfo("Guardado", "La imagen se ha guardado con éxito.")
    else:
        messagebox.showerror("Error", "No hay imagen para guardar.")

# Initialize main window
root = tk.Tk()
root.title("Image Joiner with Background")

# Initialize global variables
img1 = None
img2 = None
background_img = None
final_image = None

# Create and place widgets
btn_open1 = tk.Button(root, text="Open Image 1", command=open_image1)
btn_open1.pack()

label_img1 = tk.Label(root)
label_img1.pack()

btn_open2 = tk.Button(root, text="Open Image 2", command=open_image2)
btn_open2.pack()

label_img2 = tk.Label(root)
label_img2.pack()

btn_open_bg = tk.Button(root, text="Open Background Image", command=open_background_image)
btn_open_bg.pack()

label_background = tk.Label(root)
label_background.pack()

btn_join = tk.Button(root, text="Join Images with Background", command=join_images_with_background)
btn_join.pack()

label_result = tk.Label(root)
label_result.pack()

btn_save = tk.Button(root, text="Save Image", command=save_image)
btn_save.pack()

# Run the application
root.mainloop()