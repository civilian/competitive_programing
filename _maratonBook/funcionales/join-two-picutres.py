import tkinter as tk
from tkinter import filedialog
from PIL import Image, ImageTk

def open_image1():
    file_path = filedialog.askopenfilename()
    if file_path:
        global img1
        img1 = Image.open(file_path)
        img1.thumbnail((300, 300))  # Resize for display
        img1_tk = ImageTk.PhotoImage(img1)
        label_img1.config(image=img1_tk)
        label_img1.image = img1_tk

def open_image2():
    file_path = filedialog.askopenfilename()
    if file_path:
        global img2
        img2 = Image.open(file_path)
        img2.thumbnail((300, 300))  # Resize for display
        img2_tk = ImageTk.PhotoImage(img2)
        label_img2.config(image=img2_tk)
        label_img2.image = img2_tk

def join_images():
    if img1 and img2:
        # Ensure both images have the same height
        img1_resized = img1.resize((img1.width, max(img1.height, img2.height)))
        img2_resized = img2.resize((img2.width, max(img1.height, img2.height)))
        
        # Define the space width (adjust this value for more space)
        space_width = 200  # Sufficient space for a face in the center
        
        # Create a square canvas with a green background
        canvas_size = max(img1_resized.height, img1_resized.width + img2_resized.width + space_width)
        new_img = Image.new('RGB', (canvas_size, canvas_size), color=(0, 255, 0))  # Green background
        
        # Calculate positions to center the images horizontally and vertically
        img1_x = (canvas_size - (img1_resized.width + img2_resized.width + space_width)) // 2
        img1_y = (canvas_size - img1_resized.height) // 2
        img2_x = img1_x + img1_resized.width + space_width
        img2_y = img1_y
        
        # Paste images with a green space in between
        new_img.paste(img1_resized, (img1_x, img1_y))
        new_img.paste(img2_resized, (img2_x, img2_y))
        
        # Display the result
        new_img.thumbnail((600, 600))  # Resize for display
        new_img_tk = ImageTk.PhotoImage(new_img)
        label_result.config(image=new_img_tk)
        label_result.image = new_img_tk

        # Save the new image globally for saving later
        global final_image
        final_image = new_img

def save_image():
    if final_image:
        file_path = filedialog.asksaveasfilename(defaultextension=".png", filetypes=[("PNG files", "*.png"), ("JPEG files", "*.jpg"), ("All files", "*.*")])
        if file_path:
            final_image.save(file_path)
            tk.messagebox.showinfo("Guardado", "La imagen se ha guardado con éxito.")

# Initialize main window
root = tk.Tk()
root.title("Image Joiner")

# Initialize global variables
img1 = None
img2 = None
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

btn_join = tk.Button(root, text="Join Images", command=join_images)
btn_join.pack()

label_result = tk.Label(root)
label_result.pack()

btn_save = tk.Button(root, text="Save Image", command=save_image)
btn_save.pack()

# Run the application
root.mainloop()