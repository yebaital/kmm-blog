package code.yousef.blog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import code.yousef.blog.di.authViewModelModule
import code.yousef.blog.di.networkModule
import code.yousef.blog.theme.theme
import code.yousef.blog.utils.Constants.liveLocale
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerBaseStyle
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import de.comahe.i18n4k.Locale
import de.comahe.i18n4k.config.I18n4kConfigDefault
import de.comahe.i18n4k.i18n4k
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.css.vh
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

private const val COLOR_MODE_KEY = "blog:colorMode"

@InitSilk
fun initSilk(ctx: InitSilkContext) {
    ctx.config.initialColorMode = localStorage.getItem(COLOR_MODE_KEY)?.let { ColorMode.valueOf(it) } ?: ColorMode.LIGHT

    // Color theme
    ctx.theme.palettes = theme

    ctx.stylesheet.registerBaseStyle("body") {
        Modifier.fontFamily(
            "-apple-system", "BlinkMacSystemFont", "Segoe UI", "Roboto", "Oxygen", "Ubuntu",
            "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue", "sans-serif"
        )
    }


}

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {

    val i18n4kConfig = I18n4kConfigDefault()

    i18n4k = i18n4kConfig
    i18n4kConfig.locale = liveLocale.value?.let { Locale(it) } ?: Locale("en")

    LaunchedEffect(Unit){
        initKoin()
    }

    key(liveLocale.value) {
        SilkApp {
            val colorMode = ColorMode.current
            LaunchedEffect(colorMode) {
                localStorage.setItem(COLOR_MODE_KEY, colorMode.name)
            }

            Surface(
                SmoothColorStyle.toModifier().minHeight(100.vh)
                    .attrsModifier { attr("dir", if (liveLocale.value == "ar") "rtl" else "ltr") }) {
                content()
            }
        }
    }
}

@Composable
fun rememberLocale() = remember { liveLocale }

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        networkModule,
        authViewModelModule
    )
}