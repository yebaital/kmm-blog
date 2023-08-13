package code.yousef.blog.externals.easymde

@JsModule("easymde")
@JsNonModule
external class EasyMDE(options: dynamic) {
    fun value(): String
    fun value(text: String)
}