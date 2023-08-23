package code.yousef.blog.pages.viewmodels

import code.yousef.blog.models.PostDTO
import de.comahe.i18n4k.i18n4k
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.browser.localStorage
import org.w3c.dom.get

class CreatePostViewModel(private val client: HttpClient) {
    suspend fun createPost(dto: PostDTO): HttpStatusCode {
        val jwt = localStorage.get("JWT")
        val language = i18n4k.locale.language
        val response = client.post("http://localhost:9090/$language/blog/") {
            contentType(ContentType.Application.Json)
            headers {
                append("Authorization", "Bearer $jwt")
            }
            setBody(dto)
        }
        return response.status
    }
}