pluginManagement {
    val kotlin_version: String by settings
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
    }
    plugins {
        id("org.jetbrains.kotlin.jvm") version kotlin_version
        id("org.jetbrains.kotlin.multiplatform") version kotlin_version
        id("org.jetbrains.kotlin.plugin.serialization") version kotlin_version
        id("org.jetbrains.kotlin.js") version kotlin_version
    }
}

rootProject.name = "blog"

include(":site")
include(":shared")
include(":server")
