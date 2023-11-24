pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "compose-style-sheet"
include(":compose-stylesheet")
include(":compose-stylesheet-core")
include(":compose-stylesheet-theme")
include(":component:compose-stylesheet-appbar")
include(":component:compose-stylesheet-button")
include(":component:compose-stylesheet-icon")
include(":component:compose-stylesheet-surface")
include(":component:compose-stylesheet-text")
include(":sample:android")
