package code.yousef.blog.utils

import androidx.compose.runtime.mutableStateOf
import kotlinx.browser.localStorage
import org.jetbrains.compose.web.css.px

object Constants {
    public val liveLocale by lazy { mutableStateOf(localStorage.getItem("LOCALE")) }
    val PADDING_TOP = 10.px
    val PADDING_BOTTOM = 5.px
    val FIELD_WIDTH = 500.px
}

