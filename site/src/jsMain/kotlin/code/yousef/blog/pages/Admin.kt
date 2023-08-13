package code.yousef.blog.pages

import androidx.compose.runtime.Composable
import code.yousef.blog.components.layouts.AdminLayout
import code.yousef.blog.components.layouts.PageLayout
import com.varabyte.kobweb.core.Page
import de.comahe.i18n4k.i18n4k
import x.y.Strings

@Page
@Composable
fun AdminPage() {
    PageLayout(Strings.Admin(i18n4k.locale)) {
        AdminLayout {

        }
    }
}