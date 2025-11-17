# Quick Setup: Create New GitHub Repository for Extensions

Follow these steps to create a separate repository for your Mihon extensions:

## 📋 Step-by-Step Guide

### 1. Create New GitHub Repository

1. Go to https://github.com/new
2. **Repository name**: `mihon-extensions` (or whatever you prefer)
3. **Description**: "Custom Mihon/Tachiyomi extensions"
4. **Public** or **Private**: Choose Public (Mihon needs public access)
5. **DO NOT** initialize with README (we already have files)
6. Click **Create repository**

### 2. Initialize Local Repository

Open PowerShell in the `repo` directory:

```powershell
cd C:\Scripts\mihon-another-piece-of-candy\repo

# Initialize git repository
git init

# Add all files
git add .

# Commit
git commit -m "Initial commit: Another Piece of Candy extension"

# Set branch to main
git branch -M main
```

### 3. Push to GitHub

Replace `YOUR_USERNAME` with your GitHub username:

```powershell
# Add remote repository
git remote add origin https://github.com/YOUR_USERNAME/mihon-extensions.git

# Push to GitHub
git push -u origin main
```

### 4. Enable GitHub Pages

1. Go to your repository on GitHub
2. Click **Settings**
3. Scroll to **Pages** section (left sidebar)
4. Under **Source**, select:
   - **Branch**: `main`
   - **Folder**: `/` (root)
5. Click **Save**
6. Wait 1-2 minutes for GitHub Pages to build

### 5. Get Your Repository URL

Your extension repository will be available at:

```
https://raw.githubusercontent.com/YOUR_USERNAME/mihon-extensions/main/index.min.json
```

Or via GitHub Pages (after it's built):

```
https://YOUR_USERNAME.github.io/mihon-extensions/index.min.json
```

### 6. Add to Mihon

On your Android device:

1. Open **Mihon**
2. **Browse** → **Extension repos** (gear icon)
3. Tap **+** button
4. Paste your repository URL
5. Tap **Add**

Your extension will now appear in the Extensions list! 🎉

## 🔄 Future Updates

When you update your extension:

```powershell
# Navigate to repo directory
cd C:\Scripts\mihon-another-piece-of-candy\repo

# Copy new APK (increment version number)
Copy-Item "..\build\outputs\apk\debug\another-piece-of-candy-debug.apk" "apk\another-piece-of-candy-v2.apk"

# Update index.min.json (change version code and apk path)

# Commit and push
git add .
git commit -m "Release v1.1.0"
git push
```

Mihon will automatically detect the update!

## 📂 Keep Extension Source Separate

Your extension **source code** stays in:
```
C:\Scripts\mihon-another-piece-of-candy\
```

Your **repository** (for distribution) is just:
```
C:\Scripts\mihon-another-piece-of-candy\repo\
```

This keeps them separate - you can even have multiple repositories if you want!

## 🎯 Alternative: Use Just the `repo` Folder

If you want a completely separate repository:

```powershell
# Create a new directory
cd C:\Scripts
mkdir my-mihon-repo
cd my-mihon-repo

# Copy the repo contents
Copy-Item -Recurse "C:\Scripts\mihon-another-piece-of-candy\repo\*" .

# Initialize git
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/mihon-extensions.git
git push -u origin main
```

This way you have:
- **Source code**: `C:\Scripts\mihon-another-piece-of-candy\` (stays private)
- **Extension repo**: `C:\Scripts\my-mihon-repo\` (can be public)

---

**Ready to publish your extensions! 🚀**
