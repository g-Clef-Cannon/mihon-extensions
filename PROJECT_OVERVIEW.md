# 🍬 Another Piece of Candy - Mihon Extension

Complete Mihon/Tachiyomi extension for reading the "Another Piece of Candy" webcomic!

## ⚡ Quick Start

### What You Need
- JDK 17+
- Android SDK (API 34)
- Android device with Mihon installed

### Build in 3 Steps

1. **Add icons** (download from https://img.comicfury.com/avatars/43854.png and resize):
   - Place `ic_launcher.png` in each `src/main/res/mipmap-*` folder
   - Sizes: 48x48, 72x72, 96x96, 144x144, 192x192

2. **Build**:
   ```powershell
   .\build-extension.ps1
   ```

3. **Install**:
   - Transfer `build\outputs\apk\release\*.apk` to your phone
   - Install via Mihon → Extensions → Install from file

## 📁 Project Files

### Core Extension Code
- `AnotherPieceOfCandy.kt` - Main source implementation
  - Fetches chapter list from archive
  - Parses comic pages and images
  - Handles metadata (titles, dates)
  
- `AnotherPieceOfCandyFactory.kt` - Registers the extension
- `AnotherPieceOfCandyUrlActivity.kt` - Handles deep links

### Configuration
- `build.gradle.kts` - Build configuration and dependencies
- `AndroidManifest.xml` - Extension manifest
- `gradle.properties` - Gradle settings

### Documentation
- `README.md` - Full documentation
- `SETUP_GUIDE.md` - Quick setup for Windows
- `src/main/res/ICON_README.md` - Icon specifications

## 🔧 How It Works

### Architecture

```
Archive Page → Chapter List → Individual Comic Page → Image
     ↓              ↓                    ↓                ↓
Parse links    Extract info      Find <img> tag    Download image
```

### Key Parsing Logic

1. **Chapter List**: Scrapes `https://another-piece-of-candy.thecomicseries.com/archive/`
   - Selector: `ol li a`
   - Extracts: chapter number, title, date
   - Formats: "Chapter X - Title"

2. **Comic Page**: Parses individual comic pages
   - Selector: `div#cc img, img[src*=comicfury.com/comics]`
   - Extracts: direct image URL from ComicFury CDN

3. **Metadata**:
   - Author/Artist: NomnomNami
   - Status: Ongoing
   - Genre: Slice of Life, Comedy, Cute

### Website Structure

The extension handles this URL pattern:
```
Base: https://another-piece-of-candy.thecomicseries.com
Archive: /archive/
Chapters: /comics/pl/XXXXXXX/ or /comics/XXX/
Images: https://img.comicfury.com/comics/NNN/HASH.png
```

## 🎨 Customization

### Modify Chapter Parsing

Edit `AnotherPieceOfCandy.kt`:

```kotlin
override fun chapterFromElement(element: Element): SChapter {
    // Your custom parsing logic here
}
```

### Change Image Extraction

```kotlin
override fun pageListParse(document: Document): List<Page> {
    // Modify the selector if website changes
    val imageElement = document.selectFirst("your-selector")
    // ...
}
```

### Add Custom Filters

```kotlin
override fun getFilterList() = FilterList(
    // Add custom filters here
)
```

## 🐛 Debugging

### Test Individual Components

1. **Test Archive Parsing**:
   - Visit https://another-piece-of-candy.thecomicseries.com/archive/
   - Inspect the HTML structure
   - Verify `ol li a` selector still works

2. **Test Image Loading**:
   - Visit a specific comic page
   - Check if the image selector matches
   - Verify image URLs are accessible

### Common Issues

**Chapters not appearing**:
- Check if website HTML structure changed
- Update `chapterListSelector()`

**Images not loading**:
- Verify `pageListParse()` selector
- Check if ComicFury changed their CDN

**Build fails**:
- Verify JDK and Android SDK are installed
- Check ANDROID_HOME environment variable
- Run: `.\gradlew.bat clean build`

## 📦 Dependencies

From `build.gradle.kts`:

```kotlin
// Mihon extension library
compileOnly("com.github.tachiyomiorg:extensions-lib:1.5")

// HTML parsing
implementation("org.jsoup:jsoup:1.17.2")

// JSON serialization
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
```

## 🚀 Building for Production

### Sign the APK (Optional)

1. Create a keystore:
   ```bash
   keytool -genkey -v -keystore my-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias my-alias
   ```

2. Add signing config to `build.gradle.kts`:
   ```kotlin
   signingConfigs {
       create("release") {
           storeFile = file("my-release-key.jks")
           storePassword = "password"
           keyAlias = "my-alias"
           keyPassword = "password"
       }
   }
   ```

### Optimize Build

Add to `build.gradle.kts`:
```kotlin
buildTypes {
    named("release") {
        isMinifyEnabled = true
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
    }
}
```

## 📝 Version History

- **v1.0.0** - Initial release
  - Full chapter list support
  - Image loading
  - Date tracking
  - Metadata support

## 🤝 Contributing

Want to improve this extension?

1. Fork the repository
2. Make your changes
3. Test thoroughly
4. Submit a pull request

### Testing Checklist
- [ ] Archive page loads all chapters
- [ ] Chapter titles parse correctly
- [ ] Dates are accurate
- [ ] Images display properly
- [ ] Navigation works (first/previous/next/latest)
- [ ] Extension doesn't crash on edge cases

## 📄 License

This extension is for personal use. The webcomic content belongs to NomnomNami.

## 🔗 Links

- **Webcomic**: https://another-piece-of-candy.thecomicseries.com/
- **Mihon App**: https://mihon.app/
- **Creator**: https://nomnomnami.com/
- **Source Code**: This directory!

## 💡 Tips

- The extension checks for new chapters automatically when you refresh
- Long-press a chapter to mark it as read/unread
- Use Mihon's "Download" feature to read offline
- Check the webcomic's official site to support the creator

## 🆘 Support

If you encounter issues:

1. Check the webcomic is accessible in your browser
2. Verify you have the latest version of Mihon
3. Try reinstalling the extension
4. Check the error logs in Mihon settings
5. Review the troubleshooting section in README.md

---

Built with ❤️ for fans of Another Piece of Candy
