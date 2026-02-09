plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.telcoapp"
    compileSdk = 30

    defaultConfig {
        applicationId = "com.example.telcoapp"
        minSdk = 24
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")

    implementation(fileTree(mapOf(
        "dir" to "libs",
        "include" to listOf("*.jar")
    )))

    // AARs
    implementation(group = "", name = "pushNotification", ext = "aar")
    implementation(group = "", name = "SixDeeCVM-release_051225", ext = "aar")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Calligraphy
    implementation("io.github.inflationx:calligraphy3:3.1.1")
    implementation("io.github.inflationx:viewpump:2.0.3")

    // ButterKnife (DO NOT upgrade)
    implementation("com.jakewharton:butterknife:10.2.3")
    annotationProcessor("com.jakewharton:butterknife-compiler:10.2.3")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    implementation("com.github.aabhasr1:OtpView:v1.1.2")

    implementation("com.google.firebase:firebase-core:21.1.1")
    implementation("com.google.firebase:firebase-messaging:24.1.1")
    implementation("com.android.installreferrer:installreferrer:2.2")
}
