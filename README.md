# Mihon Extensions

Custom Mihon/Tachiyomi extensions for webcomics.

##  Available Extensions

- **Another Piece of Candy** - A webcomic extension for reading "Another Piece of Candy" by NomnomNami

##  Add Repository to Mihon

1. Open **Mihon** on your Android device
2. Go to **Browse**  **Extension repos** (gear icon)
3. Tap the **+** button
4. Enter the repository URL:

```
https://raw.githubusercontent.com/g-Clef-Cannon/mihon-extensions/main/repo/index.min.json
```

5. Tap **Add**
6. Your extensions will appear in the Extensions list!

##  Repository Structure

```
repo/               # Extension repository (for Mihon)
 index.min.json  # Extension metadata
 README.md       # Repository info
 apk/
     another-piece-of-candy-v1.apk

src/                # Extension source code
build.gradle.kts    # Build configuration
gradlew.bat         # Gradle wrapper
```

##  Building from Source

```powershell
.\gradlew.bat assembleDebug
```

The APK will be in `build/outputs/apk/debug/`

##  Documentation

- **[Build Success Report](BUILD_SUCCESS.md)** - Build information
- **[Extension Repository Guide](EXTENSION_REPOSITORY_GUIDE.md)** - Complete reference
- **[Quick GitHub Setup](QUICK_GITHUB_SETUP.md)** - Publishing guide
- **[Setup Guide](SETUP_GUIDE.md)** - Development setup

##  License

Extensions are provided as-is for personal use.
