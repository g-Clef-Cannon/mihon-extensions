# Creating a Mihon Extension Repository

This guide explains how to host your extension so Mihon can install it directly from a repository URL.

## 📦 What is an Extension Repository?

An extension repository is a JSON file hosted online that tells Mihon:
- What extensions are available
- Where to download the APK files
- Version information
- Update notifications

## 🚀 Quick Setup

### Option 1: GitHub Releases (Recommended)

This is the easiest way to host your extension repository.

#### Step 1: Create Repository Structure

```
your-repo/
├── apk/
│   └── another-piece-of-candy-v1.apk
└── index.min.json
```

#### Step 2: Create `index.min.json`

```json
[
  {
    "name": "Another Piece of Candy",
    "pkg": "eu.kanade.tachiyomi.extension.en.anotherpieceofcandy",
    "apk": "another-piece-of-candy-v1.apk",
    "lang": "en",
    "code": 1,
    "version": "1.0.0",
    "nsfw": false,
    "hasReadme": false,
    "hasChangelog": false,
    "sources": [
      {
        "name": "Another Piece of Candy",
        "lang": "en",
        "id": "5047394842157742947",
        "baseUrl": "https://another-piece-of-candy.thecomicseries.com"
      }
    ]
  }
]
```

#### Step 3: Commit and Push to GitHub

```powershell
# Initialize git repository
cd C:\Scripts\mihon-another-piece-of-candy
git init

# Create the repository structure
New-Item -ItemType Directory -Force -Path "repo\apk"

# Copy your APK
Copy-Item "build\outputs\apk\debug\another-piece-of-candy-debug.apk" "repo\apk\another-piece-of-candy-v1.apk"

# Create index.min.json (see above)
# Then commit
git add .
git commit -m "Initial extension repository"

# Push to GitHub
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/mihon-extensions.git
git push -u origin main
```

#### Step 4: Get the Raw URL

After pushing, get the raw URL for your `index.min.json`:
```
https://raw.githubusercontent.com/YOUR_USERNAME/mihon-extensions/main/repo/index.min.json
```

#### Step 5: Add to Mihon

1. Open **Mihon**
2. Go to **Browse** → **Extension repos**
3. Tap the **+** button
4. Enter your repository URL
5. Tap **Add**

Mihon will now show your extension in the Extensions list!

---

## 🌐 Option 2: Self-Hosted Server

If you have your own web server:

### Directory Structure:
```
your-domain.com/
└── mihon/
    ├── index.min.json
    └── apk/
        └── another-piece-of-candy-v1.apk
```

### Requirements:
- HTTPS enabled (required by Mihon)
- CORS headers configured
- Direct access to files (no authentication)

### Nginx Configuration:
```nginx
location /mihon/ {
    add_header Access-Control-Allow-Origin *;
    add_header Access-Control-Allow-Methods 'GET, OPTIONS';
    autoindex off;
}
```

### Repository URL:
```
https://your-domain.com/mihon/index.min.json
```

---

## 📋 index.min.json Reference

Here's the complete structure with all available fields:

```json
[
  {
    "name": "Extension Display Name",
    "pkg": "package.name.from.AndroidManifest",
    "apk": "relative-path-to-apk.apk",
    "lang": "en",
    "code": 1,
    "version": "1.0.0",
    "nsfw": false,
    "hasReadme": true,
    "hasChangelog": true,
    "sources": [
      {
        "name": "Source Name",
        "lang": "en",
        "id": "unique-source-id-from-code",
        "baseUrl": "https://source-website.com"
      }
    ]
  }
]
```

### Field Descriptions:

- **name**: Extension display name in Mihon
- **pkg**: Android package name (from `AndroidManifest.xml`)
- **apk**: Relative path to APK file from `index.min.json` location
- **lang**: Language code (`en`, `es`, `fr`, etc.)
- **code**: Version code (integer, increment for updates)
- **version**: Version string (semantic versioning)
- **nsfw**: `true` if extension contains adult content
- **hasReadme**: `true` if you have a `README.md` in the same directory
- **hasChangelog**: `true` if you have a `CHANGELOG.md`
- **sources**: Array of sources provided by this extension

### Source ID:

The `id` field must match the ID in your Kotlin code. For your extension:

```kotlin
override val id: Long = 5047394842157742947L
```

This ID was generated when we created the extension and is found in `AnotherPieceOfCandy.kt`.

---

## 🔄 Updating Your Extension

When you make changes and want to release an update:

### 1. Update Version in Code

Edit `build.gradle.kts`:
```kotlin
versionCode = 2  // Increment this
versionName = "1.1.0"  // Update version string
```

### 2. Rebuild APK

```powershell
.\gradlew.bat clean assembleDebug
```

### 3. Copy New APK

```powershell
Copy-Item "build\outputs\apk\debug\another-piece-of-candy-debug.apk" "repo\apk\another-piece-of-candy-v2.apk"
```

### 4. Update index.min.json

```json
{
  "name": "Another Piece of Candy",
  "apk": "another-piece-of-candy-v2.apk",
  "code": 2,
  "version": "1.1.0",
  ...
}
```

### 5. Commit and Push

```powershell
git add .
git commit -m "Release v1.1.0"
git push
```

Mihon will automatically detect the update and notify users!

---

## 🎯 GitHub Pages Setup (Free Hosting)

GitHub Pages provides free HTTPS hosting perfect for extension repos:

### 1. Enable GitHub Pages

1. Go to your repository on GitHub
2. Settings → Pages
3. Source: Deploy from branch
4. Branch: `main`, folder: `/repo`
5. Save

### 2. Your Repository URL

After a few minutes, your extensions will be available at:
```
https://YOUR_USERNAME.github.io/mihon-extensions/index.min.json
```

### 3. Repository Structure

```
mihon-extensions/
├── repo/
│   ├── index.min.json
│   └── apk/
│       └── another-piece-of-candy-v1.apk
└── README.md
```

---

## 🧪 Testing Your Repository

Before adding to Mihon, test your repository:

### 1. Check JSON Validity

Use an online JSON validator or:
```powershell
Get-Content repo\index.min.json | ConvertFrom-Json
```

### 2. Verify File Accessibility

Test that your files are accessible:
```powershell
Invoke-WebRequest -Uri "https://your-url/index.min.json"
Invoke-WebRequest -Uri "https://your-url/apk/your-extension.apk"
```

### 3. Check HTTPS

Mihon requires HTTPS. Verify your URL uses `https://` not `http://`.

---

## 📝 Complete Example Repository

I'll create a ready-to-use repository structure for you:

```
mihon-extensions/
├── .github/
│   └── workflows/
│       └── build.yml          # Auto-build on push
├── repo/
│   ├── index.min.json         # Extension metadata
│   ├── apk/
│   │   └── another-piece-of-candy-v1.apk
│   └── icon/
│       └── another-piece-of-candy.png
├── src/                       # Your extension source code
├── README.md                  # Repository documentation
└── build.gradle.kts
```

---

## 🔒 Security Considerations

- **HTTPS**: Always use HTTPS for your repository
- **Signing**: Consider signing your APKs for production use
- **Verification**: Users should verify the repository source
- **Updates**: Test updates before releasing
- **Privacy**: Don't collect user data without consent

---

## 💡 Tips

1. **Keep APKs Small**: Mihon downloads these over mobile networks
2. **Version Carefully**: Increment `code` for every release
3. **Test Locally**: Install APKs manually before publishing
4. **Document Changes**: Maintain a CHANGELOG.md
5. **Use GitHub Actions**: Automate builds and releases

---

## 🆘 Troubleshooting

### "Failed to fetch repository"
- Check HTTPS is enabled
- Verify JSON is valid
- Ensure CORS headers are set (if self-hosting)

### "Extension not showing"
- Verify `pkg` matches your AndroidManifest.xml
- Check `code` is higher than previous version
- Ensure APK path is correct and accessible

### "Update not detected"
- Increment `code` (not just `version`)
- Clear Mihon cache and refresh
- Verify new APK is uploaded

---

## 🎓 Next Steps

1. Create a GitHub repository
2. Copy your APK and create index.min.json
3. Enable GitHub Pages
4. Add repository URL to Mihon
5. Share your extension with others!

Your extension can now be installed by anyone with the repository URL! 🎉
