//package code.yousef.blog.components.widgets
//
//import androidx.compose.runtime.Composable
//import com.varabyte.kobweb.compose.css.*
//import com.varabyte.kobweb.compose.css.functions.invert
//import com.varabyte.kobweb.compose.css.functions.url
//import com.varabyte.kobweb.compose.foundation.layout.Column
//import com.varabyte.kobweb.compose.foundation.layout.Row
//import com.varabyte.kobweb.compose.ui.*
//import com.varabyte.kobweb.compose.ui.modifiers.*
//import com.varabyte.kobweb.silk.components.style.ComponentStyle
//import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
//import com.varabyte.kobweb.silk.components.style.toModifier
//import com.varabyte.kobweb.silk.components.text.SpanText
//import com.varabyte.kobweb.silk.theme.toSilkPalette
//import org.jetbrains.compose.web.ExperimentalComposeWebApi
//import org.jetbrains.compose.web.attributes.InputType
//import org.jetbrains.compose.web.attributes.builders.InputAttrsScope
//import org.jetbrains.compose.web.attributes.placeholder
//import org.jetbrains.compose.web.css.*
//import org.jetbrains.compose.web.css.keywords.auto
//import org.jetbrains.compose.web.dom.Input
//
//@OptIn(ExperimentalComposeWebApi::class)
//val fluentTextFieldStyle by ComponentStyle {
//    base {
//        Modifier
//            .border(style = LineStyle.None)
//            .borderBottom(2.px, LineStyle.Solid, FluentColors.primary)
//            .backgroundColor(Color.transparent)
//            .outline(style = LineStyle.None)
//            .fontSize(1.4.cssRem)
//            .width(70.vw)
//            .color(colorMode.toSilkPalette().color)
//            .transition(CSSTransition(TransitionProperty.All, 0.1.s, AnimationTimingFunction.Ease))
//    }
//    Breakpoint.MD {
//        Modifier
//            .width(auto)
//    }
//    cssRule(":focus + .inputFieldSpan, :not(:placeholder-shown) + .inputFieldSpan") {
//        Modifier
//            .fontSize(1.cssRem)
//            .color(FluentColors.primary)
//            .transform {
//                translateY((-1.2).cssRem)
//            }
//    }
//    cssRule("::-webkit-search-cancel-button") {
//        Modifier
//            .styleModifier { property("-webkit-appearance", "none") }
//            .backgroundImage(BackgroundImage.of(url(R.res.clear_icon)))
//            .height(1.em)
//            .width(1.em)
//            .backgroundSize(BackgroundSize.Contain)
//            .cursor(Cursor.Pointer)
//            .translateX(1.3.cssRem)
//            .transition(CSSTransition(TransitionProperty.All, 0.1.s, AnimationTimingFunction.EaseIn))
//            .thenIf(colorMode.isDark(), Modifier.filter(invert(1)))
//    }
//    cssRule(":focus::-webkit-search-cancel-button, input:hover::-webkit-search-cancel-button") {
//        Modifier
//            .translateX(0.cssRem)
//    }
//    cssRule("::-webkit-scrollbar") {
//        Modifier.display(DisplayStyle.None)
//    }
//}
//
//val fluentTextFieldLabelStyle by ComponentStyle {
//    base {
//        Modifier
//            .color(colorMode.toSilkPalette().color)
//            .position(Position.Absolute)
////            .top(0.px) //removed for TagInput
//            .left(1.px)
//            .fontSize(1.cssRem)
//            .pointerEvents(PointerEvents.None)
//            .transition(CSSTransition(TransitionProperty.All, 0.1.s, AnimationTimingFunction.Ease))
//    }
//}
//
//
//@Composable
//fun <K> FluentTextField(
//    type: InputType<K>,
//    label: String,
//    modifier: Modifier = Modifier,
//    error: Boolean = false,
//    errorMessage: String = "",
//    attrs: InputAttrsScope<K>.() -> Unit
//) {
//    Column(Modifier.position(Position.Relative)) {
//        Input(
//            type = type,
//            attrs = fluentTextFieldStyle.toModifier().thenIf(error, Modifier.borderColor(FluentColors.error))
//                .then(modifier).toAttrs {
//                    placeholder(" ")
//                    attrs()
//                }
//        )
//        SpanText(
//            label,
//            fluentTextFieldLabelStyle.toModifier().classNames("inputFieldSpan")
//        )
//        if (error) {
//            Row(
//                com.varabyte.kobweb.compose.ui.Modifier.color(FluentColors.error).gap(7.px).position(Position.Absolute).top(30.px)
//                    .animation(slideInFromTopKeyframes.toAnimation(0.2.s, AnimationTimingFunction.EaseOut)),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                MdiError(Modifier.fontSize(20.px), style = IconStyle.ROUNDED)
//                SpanText(errorMessage)
//            }
//        }
//    }
//}