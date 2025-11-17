# Mihon Extension: Another Piece of Candy

A Mihon/Tachiyomi extension for reading the webcomic "Another Piece of Candy" by NomnomNami.

## About

This extension allows you to read the webcomic "Another Piece of Candy" directly in the Mihon (formerly Tachiyomi) manga reader app.

- **Source**: https://another-piece-of-candy.thecomicseries.com/
- **Author**: NomnomNami
- **Status**: Ongoing

## Features

- ✅ Browse all chapters from the archive
- ✅ Read comics in order
- ✅ Automatic chapter detection and parsing
- ✅ Date tracking for new chapters
- ✅ High-quality image loading

## Building the Extension

### Prerequisites

1. **Android Studio** (latest version recommended)
2. **JDK 17** or higher
3. **Android SDK** (API level 34)

### Build Steps

1. **Clone or download** this project

2. **Open in Android Studio**:
   ```
   File → Open → Select the mihon-another-piece-of-candy folder
   ```

3. **Add Icons** (Important!):
   - Download the comic avatar from: https://img.comicfury.com/avatars/43854.png
   - Resize to the following dimensions and place in the corresponding folders:
     - `src/main/res/mipmap-mdpi/ic_launcher.png` (48x48)
     - `src/main/res/mipmap-hdpi/ic_launcher.png` (72x72)
     - `src/main/res/mipmap-xhdpi/ic_launcher.png` (96x96)
     - `src/main/res/mipmap-xxhdpi/ic_launcher.png` (144x144)
     - `src/main/res/mipmap-xxxhdpi/ic_launcher.png` (192x192)

4. **Build the APK**:
   - In Android Studio: `Build → Build Bundle(s) / APK(s) → Build APK(s)`
   - Or via command line:
     ```bash
     ./gradlew assembleRelease
     ```
   - The APK will be in: `build/outputs/apk/release/`

### Alternative: Build from Command Line

If you have Gradle installed:

```bash
cd mihon-another-piece-of-candy
./gradlew assembleRelease
```

On Windows (PowerShell):
```powershell
cd mihon-another-piece-of-candy
.\gradlew.bat assembleRelease
```

## Installing the Extension

### Method 1: Direct Installation

1. Build the APK as described above
2. Transfer the APK to your Android device
3. Open Mihon app
4. Go to `Browse → Extensions`
5. Click the install button (📦) in the top right
6. Select "Install from file"
7. Navigate to and select your APK

### Method 2: Install via ADB

```bash
adb install -r build/outputs/apk/release/another-piece-of-candy-release.apk
```

## Using the Extension

1. Open Mihon
2. Go to `Browse → Extensions`
3. Find "Another Piece of Candy" in your installed extensions
4. Tap to enable it
5. Go to `Browse → Sources`
6. Select "Another Piece of Candy"
7. Add the comic to your library
8. Enjoy reading!

## Development Notes

### Project Structure

```
mihon-another-piece-of-candy/
├── build.gradle.kts           # Build configuration
├── settings.gradle.kts         # Gradle settings
├── gradle.properties           # Gradle properties
└── src/main/
    ├── AndroidManifest.xml     # App manifest
    ├── java/eu/kanade/tachiyomi/extension/en/anotherpieceofcandy/
    │   ├── AnotherPieceOfCandy.kt          # Main source implementation
    │   ├── AnotherPieceOfCandyFactory.kt    # Extension factory
    │   └── AnotherPieceOfCandyUrlActivity.kt # URL handler
    └── res/
        └── mipmap-*/           # Icon resources
```

### Key Classes

- **AnotherPieceOfCandy.kt**: Main extension source that handles:
  - Fetching the chapter list from the archive page
  - Parsing individual comic pages
  - Extracting image URLs
  - Managing metadata (titles, dates, chapter numbers)

- **AnotherPieceOfCandyFactory.kt**: Factory class that registers the extension with Mihon

### Customization

If you want to modify the extension:

1. **Change parsing logic**: Edit `AnotherPieceOfCandy.kt`
2. **Update selectors**: Modify the CSS selectors if the website structure changes
3. **Add filters**: Implement custom `FilterList` in `getFilterList()`
4. **Metadata**: Update `mangaDetailsParse()` for different descriptions/tags

## Troubleshooting

### Build Errors

**Problem**: "Could not resolve dependencies"
- **Solution**: Make sure you have an internet connection and the Android SDK is properly configured

**Problem**: "Manifest merger failed"
- **Solution**: Check that your `AndroidManifest.xml` doesn't have conflicting attributes

### Runtime Errors

**Problem**: Extension doesn't appear in Mihon
- **Solution**: Make sure you installed the APK and enabled the extension in settings

**Problem**: "Could not find comic image"
- **Solution**: The website structure may have changed. Check the CSS selectors in `pageListParse()`

**Problem**: Chapters not loading
- **Solution**: Check your internet connection and verify the archive URL is still accessible

## Contributing

Feel free to submit issues or pull requests if you find bugs or want to add features!

## License

This extension is for personal use only. The webcomic "Another Piece of Candy" is © NomnomNami.

## Credits

- **Webcomic**: NomnomNami (https://nomnomnami.com/)
- **Extension Template**: Based on Mihon/Tachiyomi extension architecture
- **Hosting**: Comic Fury (https://comicfury.com/)

## Links

- Webcomic: https://another-piece-of-candy.thecomicseries.com/
- Mihon: https://mihon.app/
- NomnomNami: https://nomnomnami.com/
