package code.yousef.blog.pages

import androidx.compose.runtime.*
import code.yousef.blog.components.layouts.PageLayout
import com.varabyte.kobweb.core.Page
import de.comahe.i18n4k.i18n4k
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import x.y.Strings

@Page
@Composable
fun Blog() {
    PageLayout(Strings.Blog(i18n4k.locale)) {
        Text("Please enter your name")
        var name by remember { mutableStateOf("") }
        Input(
            InputType.Text,
            attrs = {
                onInput { e -> name = e.value }
            }
        )
        P()
        Text("Hello ${name.takeIf { it.isNotBlank() } ?: "World"}!")
    }
}
