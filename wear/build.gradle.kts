plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "io.homeassistant.companion.android"
        minSdkVersion(23)
        targetSdkVersion(30)

        versionName = System.getenv("VERSION") ?: "LOCAL"
        versionCode = System.getenv("VERSION_CODE")?.toIntOrNull() ?: 1

        javaCompileOptions {
            annotationProcessorOptions {
                arguments(mapOf("room.incremental" to "true"))
            }
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }

    signingConfigs {
        create("release") {
            storeFile = file(System.getenv("KEYSTORE_PATH") ?: "release_keystore.keystore")
            storePassword = System.getenv("KEYSTORE_PASSWORD") ?: ""
            keyAlias = System.getenv("KEYSTORE_ALIAS") ?: ""
            keyPassword = System.getenv("KEYSTORE_ALIAS_PASSWORD") ?: ""
            isV1SigningEnabled = true
            isV2SigningEnabled = true
        }
    }

    buildTypes {
        named("debug").configure {
            applicationIdSuffix = ".debug"
        }
        named("release").configure {
            isDebuggable = false
            isJniDebuggable = false
            isZipAlignEnabled = true
            signingConfig = signingConfigs.getByName("release")
        }
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    lintOptions {
        disable("MissingTranslation")
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.2"
    }
}

dependencies {
    implementation(project(":common"))

    // Standard Wear OS libraries
    implementation("androidx.wear:wear:1.2.0-rc01")
    // includes support for wearable specific inputs
    implementation("androidx.wear:wear-input:1.1.0")


    // Compose
    implementation("androidx.compose.ui:ui:1.0.2")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.2")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:1.0.2")
    // Material Design
    implementation("androidx.compose.material:material:1.0.2")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:1.0.2")
    implementation("androidx.compose.material:material-icons-extended:1.0.2")

    // Compose Wear
    implementation("androidx.wear.compose:compose-foundation:1.0.0-alpha05")
    // For Wear Material Design UX guidelines and specifications
    implementation("androidx.wear.compose:compose-material:1.0.0-alpha05")


}
