package code.yousef.blog.pages

import androidx.compose.runtime.Composable
import code.yousef.blog.components.layouts.AdminLayout
import code.yousef.blog.components.layouts.PageLayout
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import de.comahe.i18n4k.i18n4k
import x.y.Strings

@Page
@Composable
fun AdminPage() {
    val ctx = rememberPageContext()
//    if (jwt.value.isNullOrEmpty()) {
//        ctx.router.navigateTo("/admin/login")
//    } else {
        ctx.router.navigateTo("/admin/allposts")
//    }
    PageLayout(Strings.Admin(i18n4k.locale)) {
            AdminLayout {

            }
    }
}