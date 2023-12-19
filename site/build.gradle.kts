import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlinx.html.script

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown) apply true
    id("de.comahe.i18n4k") version "0.7.0"
    id("org.jetbrains.kotlin.plugin.serialization")
}

group = "code.yousef.blog"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            head.add {
                link(
                    href = "https://cdn.jsdelivr.net/npm/easymde/dist/easymde.min.css",
                    rel = "stylesheet"
                )
                script(src = "https://cdn.jsdelivr.net/npm/easymde/dist/easymde.min.js") {}
            }
            description.set("Powered by Kobweb")
        }
    }
}

kotlin {

    configAsKobwebApplication("blog", includeServer = false)

    @Suppress("UNUSED_VARIABLE") // Suppress spurious warnings about sourceset variables not being used
    sourceSets {

        val ktor_version: String by project
        val koin_version: String by project
        val i18nk_version = "0.7.0"

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation("de.comahe.i18n4k:i18n4k-core:$i18nk_version")
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
                implementation("de.comahe.i18n4k:i18n4k-core-js:$i18nk_version")

                // KMM
                implementation(project(":shared"))

                // Markdown editor
                implementation(npm("easymde", "2.18.0"))

                // Ktor
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-serialization:$ktor_version")
                implementation("io.ktor:ktor-client-js:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

                implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")

                // Koin
                implementation("io.insert-koin:koin-core:$koin_version")
            }
        }

    }
}

i18n4k {
    sourceCodeLocales = listOf("en", "ar")
}

//tasks.withType(type = ProcessResources::class) {
//    dependsOn(":site:generateI18n4kFiles")
//}

//tasks.getByPath(":site:kspKotlinJs").mustRunAfter(":site:generateI18n4kFiles")

kotlin.sourceSets.getByName("jsMain").kotlin.srcDir(tasks.matching { it.name == "generateI18n4kFiles" })
gradle.taskGraph.whenReady {
    allTasks
        .filter { it.hasProperty("duplicatesStrategy") } // Because it's some weird decorated wrapper that I can't cast.
        .forEach {
            it.setProperty("duplicatesStrategy", "EXCLUDE")
        }
}