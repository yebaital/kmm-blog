package code.yousef.blog.pages

import androidx.compose.runtime.Composable
import code.yousef.blog.components.layouts.PageLayout
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.navigation.Link
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun AdminPage() {
    PageLayout("ADMIN") {
        Text("This is a skeleton app used to showcase a basic site made using Kobweb")
        P()
        Link("/", "Go Home")
    }
}