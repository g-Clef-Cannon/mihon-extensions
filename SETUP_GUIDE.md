# Quick Setup Guide

## Prerequisites

Before building this extension, you need:

1. **JDK 17 or higher**
   - Download from: https://adoptium.net/
   - Or use chocolatey: `choco install temurin17`

2. **Android SDK Command Line Tools**
   - Download from: https://developer.android.com/studio#command-line-tools-only
   - Or install Android Studio: https://developer.android.com/studio

3. **Set ANDROID_HOME environment variable**
   ```powershell
   # Windows PowerShell
   [System.Environment]::SetEnvironmentVariable('ANDROID_HOME', 'C:\Users\YourName\AppData\Local\Android\Sdk', 'User')
   ```

## Quick Build (Windows)

1. Add icons to the `src/main/res/mipmap-*` folders (see ICON_README.md)

2. Build the extension:
   ```powershell
   .\gradlew.bat assembleRelease
   ```

3. Find the APK at:
   ```
   build\outputs\apk\release\another-piece-of-candy-release-unsigned.apk
   ```

4. Install on your device:
   - Transfer to phone and install directly, OR
   - Use ADB: `adb install -r build\outputs\apk\release\*.apk`

## First Time Setup

If you don't have the Gradle wrapper files, download them:

```powershell
# Create gradle wrapper
gradle wrapper --gradle-version 8.4
```

## Notes for Windows Users

- Use `.\gradlew.bat` instead of `./gradlew`
- Use backslashes `\` in paths
- If you get "execution policy" errors, run:
  ```powershell
  Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
  ```

## Minimum Android SDK Setup

You need at least these SDK components:
- Android SDK Platform 34
- Android SDK Build-Tools 34.0.0

Install via Android Studio SDK Manager or command line:
```bash
sdkmanager "platforms;android-34" "build-tools;34.0.0"
```
