package code.yousef.blog.pages.admin

import androidx.compose.runtime.*
import code.yousef.blog.components.layouts.AdminLayout
import code.yousef.blog.components.layouts.PageLayout
import code.yousef.blog.di.GetCreatePostViewModel.getCreatePostViewModel
import code.yousef.blog.externals.easymde.EasyMDE
import code.yousef.blog.models.PostDTO
import code.yousef.blog.pages.viewmodels.CreatePostViewModel
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
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.TextInput
import de.comahe.i18n4k.i18n4k
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.cols
import org.jetbrains.compose.web.attributes.multiple
import org.jetbrains.compose.web.attributes.required
import org.jetbrains.compose.web.attributes.rows
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLOptionElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.get
import x.y.Strings

@Composable
@Page
fun CreatePost(viewModel: CreatePostViewModel = getCreatePostViewModel()) {
    // TODO: Add a loading indicator
    // TODO: Add file uploads
    // TODO: Add image uploads
    val ctx = rememberPageContext()

    lateinit var easyMDE: EasyMDE

    val fieldStyle = Modifier.width(FIELD_WIDTH).height(30.px)
    var catRef: HTMLSelectElement? by remember { mutableStateOf(null) }

    val postTitle = mutableStateOf("")
    val postStatus = mutableStateOf<PostStatus>(PostStatus.DRAFT)
    val postType = mutableStateOf<PostType>(PostType.BLOGPOST)
    val postLanguage = mutableStateOf<WrittenLanguage>(WrittenLanguage.ENGLISH)
    val programmingLanguage = mutableStateOf<ProgrammingLanguage>(ProgrammingLanguage.KOTLIN)
    val categories = mutableListOf<Category>()
    val postSummary = mutableStateOf("")


    PageLayout(Strings.CreatePost(i18n4k.locale)) {
        AdminLayout {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.px),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val labelAttrs = Modifier.padding(top = PADDING_TOP, bottom = PADDING_BOTTOM).width(FIELD_WIDTH)

                Form(attrs = { id("create-post-form") }) {
                    Label(forId = "post-title", attrs = labelAttrs.toAttrs()) {
                        Text("Post Title")
                    }
                    Br()
                    TextInput(
                        modifier = fieldStyle.id("post-title"),
                        text = postTitle.value,
                        onTextChanged = { postTitle.value = it },
                        required = true
                    )
                    Br()
                    Label(forId = "post-status", attrs = labelAttrs.toAttrs()) {
                        Text("Post Status")
                    }
                    Br()
                    Select(attrs = fieldStyle.id("post-status").toAttrs {
                        required();
                        onChange { postStatus.value = PostStatus.valueOf(it.value!!) }
                    }) {
                        for (status in PostStatus.values()) {
                            Option(value = status.toString()) {
                                Text(status.toString())
                            }
                        }
                    }
                    Br()
                    Label(forId = "post-type", attrs = labelAttrs.toAttrs()) {
                        Text("Post Type")
                    }
                    Br()
                    Select(attrs = fieldStyle.id("post-type").toAttrs {
                        required();
                        onChange { postType.value = PostType.valueOf(it.value!!) }
                    }) {
                        for (type in PostType.values()) {
                            Option(value = type.toString()) {
                                Text(type.toString())
                            }
                        }
                    }
                    Br()
                    Label(forId = "post-language", attrs = labelAttrs.toAttrs()) {
                        Text("Post Language")
                    }
                    Br()
                    Select(attrs = fieldStyle.id("post-language").toAttrs {
                        required();
                        onChange { postLanguage.value = WrittenLanguage.valueOf(it.value!!) }
                    }) {
                        for (language in WrittenLanguage.values()) {
                            Option(value = language.toString()) {
                                Text(language.toString())
                            }
                        }
                    }
                    Br()
                    Label(forId = "programming-language", attrs = labelAttrs.toAttrs()) {
                        Text("Programming Language")
                    }
                    Br()
                    Select(attrs = fieldStyle.id("programming-language").toAttrs {
                        required();
                        onChange { programmingLanguage.value = ProgrammingLanguage.valueOf(it.value!!) }
                    }) {
                        for (language in ProgrammingLanguage.values()) {
                            Option(value = language.toString()) {
                                Text(language.toString())
                            }
                        }
                    }
                    Br()
                    Label(forId = "categories", attrs = labelAttrs.toAttrs()) {
                        Text("Categories")
                    }
                    Br()
                    Select(
                        attrs = Modifier.width(FIELD_WIDTH).height(150.px).id("categories")
                            .toAttrs {
                                multiple(); required();
                                ref {
                                    catRef = it
                                    onDispose { }
                                };
                            }) {
                        for (category in Category.values()) {
                            Option(value = category.toString()) {
                                Text(category.toString())
                            }
                        }
                    }
                    Br()
                    Label(forId = "post-summary", attrs = labelAttrs.toAttrs()) {
                        Text("Post Summary")
                    }
                    Br()
                    TextArea(attrs = {
                        id("post-summary"); cols(69); rows(15); style { resize(Resize.None) }; onChange {
                        postSummary.value = it.value
                    }
                    })
                    Br()
                    Label(forId = "post-body", attrs = labelAttrs.toAttrs()) {
                        Text("Post Body")
                    }
                    Br()
                    TextArea(attrs = Modifier.fillMaxSize().id("post-body").toAttrs())
                    Br()
                    LaunchedEffect(Unit) {
                        val options = js("{element: document.getElementById('post-body')}")
                        easyMDE = EasyMDE(options)
                    }
                    Br()
                    Button(
                        onClick = {

                            // Get the selected options from the multiselect
                            for (i in 0 until catRef!!.options.length) {
                                val opt = catRef!!.options[i] as HTMLOptionElement
                                if (opt.selected) {
                                    if (!categories.contains(Category.valueOf(opt.value))) {
                                        categories.add(Category.valueOf(opt.value))
                                    }
                                } else if (!opt.selected && categories.contains(Category.valueOf(opt.value))) {
                                    categories.remove(Category.valueOf(opt.value))
                                }
                            }
                            val dto = PostDTO(
                                postTitle = postTitle.value,
                                summary = postSummary.value,
                                body = easyMDE.value(),
                                status = postStatus.value,
                                type = postType.value,
                                writtenLanguage = postLanguage.value,
                                programmingLanguage = programmingLanguage.value,
                                categories = categories,
                                header = "https://unsplash.com/photos/the-night-sky-with-stars-and-the-milky-ItlpSSGhfyU"
                            )
//                            println(post)
                            CoroutineScope(Dispatchers.Default).launch {
                                val status = viewModel.createPost(dto)
                                if (status == HttpStatusCode.OK) ctx.router.navigateTo("/admin")
                            }
                        }
                    ) {
                        Text("Get")
                    }
                }

            }

        }
    }
}
