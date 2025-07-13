plugins {
    id("com.android.library") version "8.3.0"
    id("org.jetbrains.kotlin.android") version "1.9.22"
}

group = "com.github.sgreaves1"
version = "1.0.0"

android {
    namespace = "com.example.updatechecker"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // your deps
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}