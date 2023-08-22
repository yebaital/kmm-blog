package code.yousef.blog.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import code.yousef.blog.components.sections.CollapsableSection
import code.yousef.blog.components.sections.Sidebar
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.minWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.rememberPageContext
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun AdminLayout(adminContent: @Composable () -> Unit) {

    val ctx = rememberPageContext()

    key(localStorage.getItem("JWT")) {
        if (localStorage.getItem("JWT").isNullOrEmpty()) {
            ctx.router.navigateTo("/admin/login")
        } else {
            Box(
                Modifier.fillMaxHeight()
                    .minWidth(100.percent)
//            .gridTemplateColumns { size(minContent); size(2.fr) }
                    .padding(15.px)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.weight(1f)) {
                        Sidebar(
                            sections = listOf(
                                CollapsableSection("Posts", rows = listOf("All Posts", "Create Post")),
                                CollapsableSection("Snippets", rows = listOf("All Snippets", "Create Snippet")),
                                CollapsableSection("Categories", rows = listOf("All Categories", "Create Category")),
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        adminContent()
                    }
                }
            }
        }

    }


}
