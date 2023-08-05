import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown) apply true
    id("de.comahe.i18n4k") version "0.6.0"
}

group = "code.yousef.blog"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
        }
    }
}

kotlin {

    val i18nkVersion = "0.6.0"

    configAsKobwebApplication("blog", includeServer = false)

    @Suppress("UNUSED_VARIABLE") // Suppress spurious warnings about sourceset variables not being used
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation("de.comahe.i18n4k:i18n4k-core:$i18nkVersion")
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk.core)
                implementation(libs.kobweb.silk.icons.fa)
                implementation(libs.kobwebx.markdown)

                // Localization
                implementation("de.comahe.i18n4k:i18n4k-core-js:$i18nkVersion")

                // KMM
                implementation(project(":shared"))
            }
        }

    }
}

i18n4k {
    sourceCodeLocales = listOf("en", "ar")
}


tasks.withType(type = ProcessResources::class){
    dependsOn(":site:generateI18n4kFiles")
}


gradle.taskGraph.whenReady {
    allTasks
        .filter { it.hasProperty("duplicatesStrategy") } // Because it's some weird decorated wrapper that I can't cast.
        .forEach {
            it.setProperty("duplicatesStrategy", "EXCLUDE")
        }
}