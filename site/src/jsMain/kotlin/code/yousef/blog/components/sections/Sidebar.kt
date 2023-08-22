package code.yousef.blog.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaChevronDown
import com.varabyte.kobweb.silk.components.icons.fa.FaChevronUp
import com.varabyte.kobweb.silk.components.layout.Divider
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.AlignContent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Section

@Composable
fun Sidebar(
    sections: List<CollapsableSection>,
    modifier: Modifier = Modifier
) {
    val collapsedState = remember(sections) { sections.map { true }.toMutableStateList() }
    Column(modifier) {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]
            Section {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .onClick {
                            collapsedState[i] = !collapsed
                        }.width(150.px)
                ) {
                    Box(Modifier.size(20.px)) {
                        if (collapsed)
                            FaChevronDown()
                        else
                            FaChevronUp()
                    }
                    SpanText(
                        dataItem.title,
                        modifier = Modifier
                            .padding(left = 20.px, right = 10.px)
                            .weight(1f)
                    )
                }
                Divider()
            }
            if (!collapsed) {
                dataItem.rows.forEach { row ->
                    val context = rememberPageContext()
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .onClick {
                                context.router.navigateTo("/admin/" + row.replace(" ", "").lowercase())
                            }
                            .cursor(Cursor.Pointer)
                            .fillMaxWidth()
                            .alignContent(AlignContent.Center)
                    ) {
                        Spacer()
                        SpanText(
                            row,
                            modifier = Modifier.padding(left = 10.px, right = 10.px).fillMaxWidth()
                        )
                    }
                    Divider()
                }
            }
        }
    }

}


data class CollapsableSection(val title: String, val rows: List<String>)