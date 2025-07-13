plugins {
    id("com.android.library") version "8.3.0"
    id("org.jetbrains.kotlin.android") version "1.9.22"
}

android {
    namespace = "com.example.updatechecker"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }
}

dependencies {
    // your deps
}