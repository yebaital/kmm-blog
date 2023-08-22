package code.yousef.blog.viewmodels

import code.yousef.blog.models.AuthResponseDTO
import code.yousef.blog.models.SignInDTO
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.browser.localStorage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AuthViewModel(private val client: HttpClient) {

    suspend fun authenticate(username: String, password: String): HttpStatusCode {
        val credentials = SignInDTO(username, password)
        val response: HttpResponse = client.post("http://localhost:9090/users/login") {
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(credentials))
        }

        if (response.status != HttpStatusCode.OK) {
            throw Exception("Authentication failed with status ${response.status}")
        }

        val tokenResponse: AuthResponseDTO = AuthResponseDTO(response.bodyAsText())
        val token = tokenResponse.token
        // Store the token
        localStorage.setItem("JWT", token)

        return response.status
    }

}