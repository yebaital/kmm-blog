package code.yousef.blog.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import code.yousef.blog.rememberLocale
import com.varabyte.kobweb.silk.components.forms.Button
import de.comahe.i18n4k.getDisplayNameInLocale
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.dom.Text
import x.y.Strings


@Composable
fun ChangeLocaleButton() {
    var localeR by rememberLocale()

    Strings.locales.forEach { locale ->
        Button(
            onClick = {
                it.preventDefault()
                localStorage.setItem("LOCALE", locale.language)
                localeR = locale.language
                println("Locale: $localeR")

            }
        ) {
            Text(locale.getDisplayNameInLocale())
        }
    }

}