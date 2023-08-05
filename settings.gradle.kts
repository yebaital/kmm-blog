pluginManagement {
    val quarkusPluginVersion: String by settings
    val quarkusPluginId: String by settings
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
    }
    plugins {
        id(quarkusPluginId) version quarkusPluginVersion
        id("org.jetbrains.kotlin.jvm") version "1.8.21"
    }
}

rootProject.name = "blog"

include(":site")
include("shared")
include(":server")
