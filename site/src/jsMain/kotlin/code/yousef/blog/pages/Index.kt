package code.yousef.blog.pages

import androidx.compose.runtime.Composable
import code.yousef.blog.components.layouts.PageLayout
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.core.Page
import de.comahe.i18n4k.i18n4k
import org.jetbrains.compose.web.dom.Text
import x.y.Strings


@Page
@Composable
fun HomePage() {
    PageLayout(title = Strings.Home(i18n4k.locale)){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) { Text("Under Construction") }
    }

}
