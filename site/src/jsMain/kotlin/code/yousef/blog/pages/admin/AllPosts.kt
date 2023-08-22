package code.yousef.blog.pages.admin

import androidx.compose.runtime.Composable
import code.yousef.blog.components.layouts.AdminLayout
import code.yousef.blog.components.layouts.PageLayout
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.core.Page
import de.comahe.i18n4k.i18n4k
import org.jetbrains.compose.web.dom.Text
import x.y.Strings

@Composable
@Page
fun AllPosts() {
//    val ctx = rememberPageContext()
//    LaunchedEffect(Unit)  {
//        if (localStorage.getItem("JWT").isNullOrEmpty()) {
//            ctx.router.navigateTo("/admin/login")
//        }
//    }
    PageLayout(Strings.AllPosts(i18n4k.locale)) {
        AdminLayout {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text("Under Construction")
            }
        }
    }
}