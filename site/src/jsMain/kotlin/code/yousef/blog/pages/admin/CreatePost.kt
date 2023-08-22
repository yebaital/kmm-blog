package code.yousef.blog.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import code.yousef.blog.components.layouts.AdminLayout
import code.yousef.blog.components.layouts.PageLayout
import code.yousef.blog.externals.easymde.EasyMDE
import code.yousef.blog.shared.common.main.models.enums.*
import code.yousef.blog.utils.Constants.FIELD_WIDTH
import code.yousef.blog.utils.Constants.PADDING_BOTTOM
import code.yousef.blog.utils.Constants.PADDING_TOP
import com.varabyte.kobweb.compose.css.Resize
import com.varabyte.kobweb.compose.css.resize
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Button
import de.comahe.i18n4k.i18n4k
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.cols
import org.jetbrains.compose.web.attributes.multiple
import org.jetbrains.compose.web.attributes.rows
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*
import x.y.Strings

@Composable
@Page
fun CreatePost() {

    lateinit var easyMDE: EasyMDE

    val fieldStyle = Modifier.width(FIELD_WIDTH).height(30.px)

    PageLayout(Strings.CreatePost(i18n4k.locale)) {
        AdminLayout {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.px),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val labelAttrs = Modifier.padding(top = PADDING_TOP, bottom = PADDING_BOTTOM).width(FIELD_WIDTH)

                Label(forId = "post-title", attrs = labelAttrs.toAttrs()) {
                    Text("Post Title")
                }
                Input(type = InputType.Text, attrs = fieldStyle.id("post-title").toAttrs())

                Label(forId = "post-status", attrs = labelAttrs.toAttrs()) {
                    Text("Post Status")
                }
                Select(attrs = fieldStyle.id("post-status").toAttrs()) {
                    for (status in PostStatus.values()) {
                        Option(value = status.toString()) {
                            Text(status.toString())
                        }
                    }
                }

                Label(forId = "post-type", attrs = labelAttrs.toAttrs()) {
                    Text("Post Type")
                }
                Select(attrs = fieldStyle.id("post-type").toAttrs()) {
                    for (type in PostType.values()) {
                        Option(value = type.toString()) {
                            Text(type.toString())
                        }
                    }
                }

                Label(forId = "post-language", attrs = labelAttrs.toAttrs()) {
                    Text("Post Language")
                }
                Select(attrs = fieldStyle.id("post-language").toAttrs()) {
                    for (language in WrittenLanguage.values()) {
                        Option(value = language.toString()) {
                            Text(language.toString())
                        }
                    }
                }

                Label(forId = "programming-language", attrs = labelAttrs.toAttrs()) {
                    Text("Programming Language")
                }
                Select(attrs = fieldStyle.id("programming-language").toAttrs()) {
                    for (language in ProgrammingLanguage.values()) {
                        Option(value = language.toString()) {
                            Text(language.toString())
                        }
                    }
                }

                Label(forId = "programming-language", attrs = labelAttrs.toAttrs()) {
                    Text("Programming Language")
                }
                Select(
                    attrs = Modifier.width(FIELD_WIDTH).height(150.px).id("programming-language")
                        .toAttrs { multiple() }) {
                    for (category in Category.values()) {
                        Option(value = category.toString()) {
                            Text(category.toString())
                        }
                    }
                }

                Label(forId = "post-summary", attrs = labelAttrs.toAttrs()) {
                    Text("Post Summary")
                }
                TextArea(attrs = { id("post-summary"); cols(69); rows(15); style { resize(Resize.None) } })

                Label(forId = "post-body", attrs = labelAttrs.toAttrs()) {
                    Text("Post Body")
                }
                TextArea(attrs = Modifier.fillMaxSize().id("post-body").toAttrs())

                LaunchedEffect(Unit) {
                    val options = js("{element: document.getElementById('post-body')}")
                    easyMDE = EasyMDE(options)
                }
                Button(onClick = { println(easyMDE.value()) }) { Text("Get") }
            }

        }
    }
}
