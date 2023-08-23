package code.yousef.blog.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import code.yousef.blog.di.GetAuthViewModel.getAuthViewModel
import code.yousef.blog.utils.Constants.FIELD_WIDTH
import code.yousef.blog.utils.Constants.PADDING_BOTTOM
import code.yousef.blog.utils.Constants.PADDING_TOP
import code.yousef.blog.pages.viewmodels.AuthViewModel
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.Input
import io.ktor.http.*
import kotlinx.browser.localStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text


@Composable
@Page
fun LogIn(viewModel: AuthViewModel = getAuthViewModel()) {

    val ctx = rememberPageContext()

    LaunchedEffect(Unit) {
        if (localStorage.getItem("JWT").isNullOrEmpty()) {
            ctx.router.navigateTo("/admin/login")
        }
    }
//    if (rememberJWT() != null) {
//        val ctx = rememberPageContext()
//        ctx.router.navigateTo("/admin/allposts")
//    }

    val fieldStyle = Modifier.width(FIELD_WIDTH).height(30.px)

    val username = mutableStateOf("")
    val password = mutableStateOf("")

    Column(
        modifier = Modifier.fillMaxSize().padding(20.px),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val labelAttrs = Modifier.padding(top = PADDING_TOP, bottom = PADDING_BOTTOM).width(FIELD_WIDTH)

        Label(forId = "username", attrs = labelAttrs.toAttrs()) {
            Text("Username")
        }
        Input(
            type = InputType.Text,
            modifier = fieldStyle.id("username"),
            value = username.value,
            onValueChanged = { username.value = it })

        Label(forId = "password", attrs = labelAttrs.toAttrs()) {
            Text("Password")
        }
        Input(
            type = InputType.Password,
            modifier = fieldStyle.id("password"),
            value = password.value,
            onValueChanged = { password.value = it })
        P()
        Button(onClick = {
            CoroutineScope(Dispatchers.Default).launch {
                val status = viewModel.authenticate(username.value, password.value)
                if (status == HttpStatusCode.OK) ctx.router.navigateTo("/admin")
                println(status)
            }
        }
        ) {
            Text("Log In")
        }
    }
}

