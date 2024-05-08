plugins {
    alias(libs.plugins.androidApplication)
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
//    id("com.google.gms.google-services")
}

android {
    namespace = "com.team21.phishingfence"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.team21.phishingfence"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation("androidx.core:core-ktx:+")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //noinspection UseTomlInstead
    implementation ("com.deepl.api:deepl-java:1.5.0")
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("io.github.youth5201314:banner:2.2.3")

    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$room_version")

    // optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")

    implementation("com.github.bumptech.glide:glide:4.13.2")
    kapt("com.github.bumptech.glide:compiler:4.13.2")

    // Import the BoM for the Firebase platform
//    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))

    // Add the dependency for the Realtime Database library
    // When using the BoM, you don't specify versions in Firebase library dependencies
//    implementation("com.google.firebase:firebase-database")
//
//    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
//    implementation("com.google.firebase:firebase-analytics")

    // AWS Lambda SDK
    implementation("com.amazonaws:aws-android-sdk-lambda:2.43.0")
}