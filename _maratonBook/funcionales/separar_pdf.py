import PyPDF2
import os

def separar_pdf(ruta_pdf, carpeta_salida):
    # Crear la carpeta de salida si no existe
    if not os.path.exists(carpeta_salida):
        os.makedirs(carpeta_salida)

    # Abrir el archivo PDF
    with open(ruta_pdf, 'rb') as archivo_pdf:
        lector_pdf = PyPDF2.PdfReader(archivo_pdf)
        num_paginas = len(lector_pdf.pages)

        # Separar cada página y guardarla como un nuevo PDF
        for i in range(num_paginas):
            escritor_pdf = PyPDF2.PdfWriter()
            escritor_pdf.add_page(lector_pdf.pages[i])

            # Crear el nombre del nuevo archivo PDF
            nombre_archivo = f'pagina_{i + 1}.pdf'
            ruta_archivo = os.path.join(carpeta_salida, nombre_archivo)

            # Guardar la página como un nuevo PDF
            with open(ruta_archivo, 'wb') as nuevo_pdf:
                escritor_pdf.write(nuevo_pdf)

            print(f'Página {i + 1} guardada como {ruta_archivo}')

# Ejemplo de uso
ruta_pdf = 'tu_archivo.pdf'  # Cambia esto por la ruta de tu archivo PDF
carpeta_salida = 'salida_pdf'  # Carpeta donde se guardarán las páginas
separar_pdf(ruta_pdf, carpeta_salida)