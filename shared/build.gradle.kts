import com.varabyte.kobweb.gradle.library.util.configAsKobwebLibrary

plugins {
    kotlin("multiplatform") version "1.9.21"
    alias(libs.plugins.kobweb.library)
}

kotlin {
    configAsKobwebLibrary()

    jvm("server") {
        withJava()
    }
    js(IR)

    @Suppress("UNUSED_VARIABLE") // Suppress spurious warnings about sourceset variables not being used
    sourceSets {
        val commonMain by getting
        val serverMain by getting
        val jsMain by getting
    }
}