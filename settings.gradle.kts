pluginManagement {
    repositories {
        plugins {
            id("com.android.application") version "8.4.1" // Ensure this version matches
            id("org.jetbrains.kotlin.android") version "1.8.21"
        }
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SoundS"
include(":app")
 