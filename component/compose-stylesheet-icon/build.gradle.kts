plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.maven.publish)
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }
    jvm("desktop")
    iosX64("uikitX64")
    iosArm64("uikitArm64")
    iosSimulatorArm64("uikitSimArm64")
    macosX64()
    macosArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.foundation)
                implementation(project(":compose-stylesheet-core"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        all {
            languageSettings.optIn(
                "com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi",
            )
        }
    }
}

android {
    namespace = "com.moriatsushi.compose.stylesheet.text"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
