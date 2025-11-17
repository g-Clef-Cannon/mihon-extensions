plugins {
    id("com.android.application") version "8.1.4"
    kotlin("android") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
}

android {
    compileSdk = 34
    namespace = "eu.kanade.tachiyomi.extension.en.anotherpieceofcandy"

    defaultConfig {
        minSdk = 21
        targetSdk = 33
        applicationId = "eu.kanade.tachiyomi.extension.en.anotherpieceofcandy"
        versionCode = 1
        versionName = "1.0.0"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")
    
    // Mihon/Tachiyomi dependencies
    compileOnly("androidx.preference:preference-ktx:1.2.1")
    
    // For HTML parsing
    implementation("org.jsoup:jsoup:1.17.2")
    
    // Kotlin serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    
    // OkHttp for networking (required by extensions)
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
}
