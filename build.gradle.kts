plugins {
//    alias(libs.plugins.kotlin.multiplatform) apply false
    kotlin("multiplatform") apply false
    kotlin("js") apply false
    kotlin("jvm") apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.kobweb.library) apply false
    alias(libs.plugins.kobwebx.markdown) apply false
}

subprojects {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
        mavenLocal()
    }
}