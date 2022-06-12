plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.bubelov.tor"
        minSdk = 30
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Tor service
    implementation("info.guardianproject:tor-android:0.4.7.7")

    // Allows controlling a Tor instance via its control port
    implementation("info.guardianproject:jtorctl:0.4.5.7")

    // Material Design components
    implementation("com.google.android.material:material:1.6.1")

    // Helps to flatten view hierarchies
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Modern HTTP client
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
}