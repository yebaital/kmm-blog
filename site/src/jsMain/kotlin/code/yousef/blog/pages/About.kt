package code.yousef.blog.pages

import androidx.compose.runtime.Composable
import code.yousef.blog.components.layouts.PageLayout
import com.varabyte.kobweb.core.Page
import de.comahe.i18n4k.i18n4k
import org.jetbrains.compose.web.dom.Text
import x.y.Strings

@Page
@Composable
fun AboutPage() {
    PageLayout(Strings.About(i18n4k.locale)) {
        Text("About Page")
    }
}
