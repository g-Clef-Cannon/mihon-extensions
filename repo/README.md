# Mihon Extension Repository

This repository hosts custom Mihon/Tachiyomi extensions.

## 📦 Available Extensions

### Another Piece of Candy
- **Language**: English
- **Version**: 1.0.0
- **Source**: https://another-piece-of-candy.thecomicseries.com
- **Type**: Webcomic

A Mihon extension for reading "Another Piece of Candy" webcomic.

## 🚀 How to Use This Repository

### Add to Mihon:

1. Open **Mihon** app on your Android device
2. Go to **Browse** → **Extension repos** (gear icon at top)
3. Tap the **+** button
4. Enter the repository URL (see below)
5. Tap **Add**

### Repository URL:

Once this repository is hosted, your URL will be:

**GitHub Pages**: `https://YOUR_USERNAME.github.io/REPO_NAME/repo/index.min.json`

**GitHub Raw**: `https://raw.githubusercontent.com/YOUR_USERNAME/REPO_NAME/main/repo/index.min.json`

## 📁 Repository Structure

```
repo/
├── index.min.json          # Extension metadata
└── apk/
    └── another-piece-of-candy-v1.apk
```

## 🔄 Updating Extensions

When a new version is released:

1. The `index.min.json` will be updated with the new version
2. Mihon will automatically detect the update
3. Users will be notified to update

## 🛠️ Building from Source

This repository also contains the source code for the extensions. To build:

```bash
./gradlew assembleDebug
```

The APK will be generated in `build/outputs/apk/debug/`.

## 📝 License

Extensions are provided as-is for personal use.

## 🆘 Support

For issues with specific extensions, please check the source website or open an issue.
