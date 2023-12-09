pluginManagement {

    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
    }
    plugins {
        id("org.jetbrains.kotlin.jvm") version "1.9.21"
    }
}

rootProject.name = "blog"

include(":site")
include(":shared")
include(":server")
