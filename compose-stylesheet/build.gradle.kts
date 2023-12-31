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
                implementation(compose.runtime)
                api(project(":compose-stylesheet-core"))
                api(project(":compose-stylesheet-theme"))
                api(project(":component:compose-stylesheet-appbar"))
                api(project(":component:compose-stylesheet-button"))
                api(project(":component:compose-stylesheet-divider"))
                api(project(":component:compose-stylesheet-icon"))
                api(project(":component:compose-stylesheet-surface"))
                api(project(":component:compose-stylesheet-text"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.moriatsushi.compose.stylesheet"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
