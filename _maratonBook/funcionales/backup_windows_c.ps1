# Ejecutar como administrador
if (-not ([Security.Principal.WindowsPrincipal][Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)) {
    Write-Host "Este script requiere permisos de administrador" -ForegroundColor Red
    exit
}

# Definir rutas y variables
$SourcePath = "C:\Users\$env:USERNAME"  # Nótese la barra correcta
$BackupDrive = "D:\Backups"            # Cambiado a D: y con barra correcta
$Date = Get-Date -Format "yyyyMMdd_HHmm"
$BackupFolder = Join-Path $BackupDrive "Backup_$Date"

# Verificar y crear directorio de backup
Write-Host "Verificando rutas..." -ForegroundColor Cyan

# Verificar si la unidad existe
$driveLetter = $BackupDrive.Substring(0, 2)
if (-not (Test-Path $driveLetter)) {
    Write-Host "Error: La unidad $driveLetter no existe" -ForegroundColor Red
    Write-Host "Unidades disponibles:"
    Get-PSDrive -PSProvider FileSystem | Format-Table Name, Root
    exit
}

# Crear estructura de directorios
try {
    if (-not (Test-Path $BackupDrive)) {
        New-Item -ItemType Directory -Path $BackupDrive -Force
        Write-Host "Creado directorio principal de backup: $BackupDrive" -ForegroundColor Green
    }

    if (-not (Test-Path $BackupFolder)) {
        New-Item -ItemType Directory -Path $BackupFolder -Force
        Write-Host "Creado directorio de backup actual: $BackupFolder" -ForegroundColor Green
    }
}
catch {
    Write-Host "Error creando directorios: $($_.Exception.Message)" -ForegroundColor Red
    exit
}

# Copiar carpetas importantes
$FoldersToBackup = @("Documents", "Desktop", "Pictures", "Videos", "Downloads", "Music")
$successfulCopies = @()
$failedCopies = @()

foreach ($Folder in $FoldersToBackup) {
    $Source = Join-Path $SourcePath $Folder
    $Destination = Join-Path $BackupFolder $Folder
    
    Write-Host "`nProcesando $Folder..." -ForegroundColor Cyan
    
    # Verificar si la carpeta de origen existe
    if (Test-Path $Source) {
        # Crear la carpeta de destino
        try {
            if (-not (Test-Path $Destination)) {
                New-Item -ItemType Directory -Path $Destination -Force | Out-Null
            }
            
            # Copiar los archivos
            Write-Host "Copiando $Folder desde $Source a $Destination"
            Copy-Item -Path "$Source\*" -Destination $Destination -Recurse -Force -ErrorAction Stop
            $successfulCopies += $Folder
            Write-Host "Copia de $Folder completada" -ForegroundColor Green
        }
        catch {
            $failedCopies += "$Folder : $($_.Exception.Message)"
            Write-Host "Error copiando $Folder : $($_.Exception.Message)" -ForegroundColor Red
        }
    }
    else {
        $failedCopies += "$Folder : Carpeta no encontrada"
        Write-Host "No se encuentra la carpeta $Folder en $Source" -ForegroundColor Yellow
    }
}

# Registrar log
$LogFile = Join-Path $BackupFolder "Backup_Log_$Date.txt"
try {
    $logContent = @"
Resumen de Backup - $(Get-Date)
------------------------
Carpetas copiadas exitosamente:
$($successfulCopies -join "`n")

Carpetas con errores:
$($failedCopies -join "`n")

Ruta de backup: $BackupFolder
"@
    
    $logContent | Out-File $LogFile -Force
    Write-Host "`nLog creado en $LogFile" -ForegroundColor Green
}
catch {
    Write-Host "Error creando archivo de log: $($_.Exception.Message)" -ForegroundColor Red
}

# Mostrar resumen
Write-Host "`n=== Resumen del Backup ===" -ForegroundColor Cyan
Write-Host "Carpetas procesadas exitosamente: $($successfulCopies.Count)" -ForegroundColor Green
Write-Host "Carpetas con errores: $($failedCopies.Count)" -ForegroundColor Yellow
Write-Host "Ubicación del backup: $BackupFolder"
Write-Host "Archivo de log: $LogFile"

# Calcular tamaño total del backup si el directorio existe
if (Test-Path $BackupFolder) {
    try {
        $totalSize = (Get-ChildItem $BackupFolder -Recurse | Measure-Object -Property Length -Sum).Sum
        if ($totalSize) {
            Write-Host "Tamaño total del backup: $([math]::Round($totalSize/1GB, 2)) GB"
        } else {
            Write-Host "El backup está vacío" -ForegroundColor Yellow
        }
    }
    catch {
        Write-Host "No se pudo calcular el tamaño total del backup: $($_.Exception.Message)" -ForegroundColor Yellow
    }
} else {
    Write-Host "No se puede calcular el tamaño: El directorio de backup no existe" -ForegroundColor Red
}

Write-Host "`nPresione cualquier tecla para salir..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")