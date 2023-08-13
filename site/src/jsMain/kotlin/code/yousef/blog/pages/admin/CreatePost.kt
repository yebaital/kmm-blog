package code.yousef.blog.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import code.yousef.blog.components.layouts.AdminLayout
import code.yousef.blog.components.layouts.PageLayout
import code.yousef.blog.externals.easymde.EasyMDE
import com.varabyte.kobweb.compose.css.AlignSelf
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignSelf
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.base
import de.comahe.i18n4k.i18n4k
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.TextArea
import x.y.Strings

val CreatePostStyle by ComponentStyle.base {
    Modifier.alignSelf(AlignSelf.Center).fillMaxWidth()
}

@Composable
@Page
fun CreatePost() {
    val options = js("{}")
    lateinit var easyMDE: EasyMDE

    PageLayout(Strings.CreatePost(i18n4k.locale)) {
        AdminLayout {

            TextArea(attrs = Modifier.fillMaxSize().id("post-body").toAttrs())

            LaunchedEffect(Unit) {
                easyMDE = EasyMDE(options)
            }
            Button(onClick = { println(easyMDE.value()) }) { Text("Get") }
        }
    }

}
