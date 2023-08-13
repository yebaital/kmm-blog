package code.yousef.blog.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import code.yousef.blog.rememberLocale
import code.yousef.blog.theme.DarkTheme
import code.yousef.blog.theme.LightTheme
import com.varabyte.kobweb.compose.dom.ElementTarget
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.fa.FaLightbulb
import com.varabyte.kobweb.silk.components.icons.fa.FaMoon
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.overlay.PopupPlacement
import com.varabyte.kobweb.silk.components.overlay.Tooltip
import com.varabyte.kobweb.silk.components.style.*
import com.varabyte.kobweb.silk.components.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.rememberColorMode
import de.comahe.i18n4k.i18n4k
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text
import x.y.Strings

val NavHeaderStyle by ComponentStyle.base(extraModifiers = { SmoothColorStyle.toModifier() }) {
    Modifier
        .fillMaxWidth()
        .height(50.px)
        // Intentionally invert the header colors from the rest of the page
        .backgroundColor(if (colorMode == ColorMode.DARK) DarkTheme.NAVBAR.color else LightTheme.NAVBAR.color)
}

val NavItemStyle by ComponentStyle {
    // Intentionally invert the header colors from the rest of the page
    val linkColor = if (colorMode == ColorMode.DARK) DarkTheme.NAVBAR_LINK.color else LightTheme.NAVBAR_LINK.color

    base { Modifier.margin(leftRight = 15.px) }

    link { Modifier.color(linkColor) }
    visited { Modifier.color(linkColor) }
}

val NavButtonVariant by NavItemStyle.addVariant {
    base { Modifier.padding(0.px).borderRadius(50.percent).size(35.px) }
}

@Composable
private fun NavLink(path: String, text: String) {
    Link(path, text, NavItemStyle.toModifier(), UndecoratedLinkVariant)
}

@Composable
fun NavHeader() {

    var colorMode by rememberColorMode()
    var localeR by rememberLocale()

    Box(NavHeaderStyle.toModifier()) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavLink("/", Strings.Home(i18n4k.locale))
            NavLink("/about", Strings.About(i18n4k.locale))
            NavLink("/blog", Strings.Blog(i18n4k.locale))
            NavLink("/markdown", "MARKDOWN")
            NavLink("/admin", Strings.Admin(i18n4k.locale))
            Spacer()

            Button(
                onClick = {
                    when (localeR) {
                        "en" -> {
                            localStorage.setItem("LOCALE", "ar")
                            localeR = "ar"
                        }
                        "ar" -> {
                            localStorage.setItem("LOCALE", "en")
                            localeR = "en"
                        }
                    }
                },
                NavItemStyle.toModifier(NavButtonVariant)
            ) {
                Box(Modifier.margin(8.px)) {
                    when (localeR) {
                        "en" -> Text("Ar")
                        "ar" -> Text("En")
                    }
                }
            }
            Tooltip(ElementTarget.PreviousSibling, "Switch language", placement = PopupPlacement.BottomRight)

            Button(
                onClick = { colorMode = colorMode.opposite() },
                NavItemStyle.toModifier(NavButtonVariant)
            ) {
                Box(Modifier.margin(8.px)) {
                    when (colorMode) {
                        ColorMode.LIGHT -> FaMoon()
                        ColorMode.DARK -> FaLightbulb()
                    }
                }
            }
            Tooltip(ElementTarget.PreviousSibling, "Toggle color mode", placement = PopupPlacement.BottomRight)
        }
    }
}
