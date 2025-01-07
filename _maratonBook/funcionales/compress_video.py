import os
import subprocess
from tkinter import Tk, filedialog, Button, Label, DoubleVar, Scale

# Function to select a video file
def select_file():
    global file_path
    file_path = filedialog.askopenfilename(
        filetypes=[("Video Files", "*.mp4;*.mkv;*.avi;*.mov;*.flv")]
    )
    if file_path:
        input_label.config(text=f"Selected: {os.path.basename(file_path)}")

# Function to compress the video using FFmpeg
def compress_video():
    if not file_path:
        status_label.config(text="Please select a video file first.", fg="red")
        return

    try:
        # Get the compression factor from the slider
        compression_factor = compression_var.get()

        # Generate the output file name
        output_file = os.path.splitext(file_path)[0] + "_compressed.mp4"

        # FFmpeg command to compress the video
        command = [
            "ffmpeg",
            "-i", file_path,            # Input file
            "-vcodec", "libx264",       # Video codec
            "-crf", str(int(compression_factor)),  # Compression level (lower is higher quality)
            "-preset", "slow",          # Encoding speed/quality tradeoff
            output_file                 # Output file
        ]

        # Run the FFmpeg command
        subprocess.run(command, check=True)

        status_label.config(text=f"Compression successful: {output_file}", fg="green")
    except subprocess.CalledProcessError as e:
        status_label.config(text=f"Error during compression: {e}", fg="red")
    except Exception as e:
        status_label.config(text=f"Error: {e}", fg="red")

# GUI setup
root = Tk()
root.title("Video Compressor")
root.geometry("400x300")

file_path = None

# Input label
input_label = Label(root, text="No file selected.", wraplength=300)
input_label.pack(pady=10)

# Select file button
select_button = Button(root, text="Select Video File", command=select_file)
select_button.pack(pady=5)

# Compression factor slider
compression_var = DoubleVar(value=23)
compression_label = Label(root, text="Compression Level (CRF, 0-51)")
compression_label.pack(pady=5)
compression_slider = Scale(
    root, from_=0, to=51, orient="horizontal", variable=compression_var
)
compression_slider.pack(pady=5)

# Compress video button
compress_button = Button(root, text="Compress Video", command=compress_video)
compress_button.pack(pady=5)

# Status label
status_label = Label(root, text="", wraplength=300)
status_label.pack(pady=10)

# Run the application
root.mainloop()
