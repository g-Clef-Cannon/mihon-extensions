# Build script for Windows PowerShell
# This script builds the Mihon extension for Another Piece of Candy

Write-Host "Building Mihon Extension: Another Piece of Candy" -ForegroundColor Cyan
Write-Host "=================================================" -ForegroundColor Cyan
Write-Host ""

# Check if icons exist
$iconDirs = @(
    "src\main\res\mipmap-mdpi",
    "src\main\res\mipmap-hdpi",
    "src\main\res\mipmap-xhdpi",
    "src\main\res\mipmap-xxhdpi",
    "src\main\res\mipmap-xxxhdpi"
)

$missingIcons = $false
foreach ($dir in $iconDirs) {
    if (-not (Test-Path "$dir\ic_launcher.png")) {
        Write-Host "⚠️  Missing icon: $dir\ic_launcher.png" -ForegroundColor Yellow
        $missingIcons = $true
    }
}

if ($missingIcons) {
    Write-Host ""
    Write-Host "Please add icon files before building. See src\main\res\ICON_README.md" -ForegroundColor Yellow
    Write-Host "You can still build, but the extension won't have an icon." -ForegroundColor Yellow
    Write-Host ""
    $continue = Read-Host "Continue anyway? (y/n)"
    if ($continue -ne "y") {
        exit 1
    }
}

# Check for Gradle wrapper
if (-not (Test-Path "gradlew.bat")) {
    Write-Host "Gradle wrapper not found. Creating..." -ForegroundColor Yellow
    gradle wrapper --gradle-version 8.4
}

Write-Host ""
Write-Host "Building APK..." -ForegroundColor Green

# Run Gradle build
& .\gradlew.bat assembleRelease

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "✅ Build successful!" -ForegroundColor Green
    Write-Host ""
    Write-Host "APK location:" -ForegroundColor Cyan
    Write-Host "  build\outputs\apk\release\" -ForegroundColor White
    Write-Host ""
    Write-Host "To install:" -ForegroundColor Cyan
    Write-Host "  1. Transfer APK to your Android device" -ForegroundColor White
    Write-Host "  2. Install via Mihon's extension installer" -ForegroundColor White
    Write-Host "  OR" -ForegroundColor White
    Write-Host "  adb install -r build\outputs\apk\release\*.apk" -ForegroundColor White
} else {
    Write-Host ""
    Write-Host "❌ Build failed. Check the errors above." -ForegroundColor Red
    exit 1
}
