package code.yousef.blog.components.layouts

import androidx.compose.runtime.Composable
import code.yousef.blog.components.sections.CollapsableSection
import code.yousef.blog.components.sections.Sidebar
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun AdminLayout(adminContent: @Composable () -> Unit) {
    Box(
        Modifier.fillMaxHeight()
            .minWidth(100.percent)
//            .gridTemplateColumns { size(minContent); size(2.fr) }
            .padding(15.px)
    ) {
        Row {
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
            Column(Modifier.fillMaxSize()) {
                adminContent()
            }


        }
    }
}
