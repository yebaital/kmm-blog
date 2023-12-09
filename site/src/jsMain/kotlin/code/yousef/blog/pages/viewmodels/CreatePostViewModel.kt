package code.yousef.blog.pages.viewmodels

import code.yousef.blog.models.PostDTO
import com.varabyte.kobweb.compose.file.readBytes
import de.comahe.i18n4k.i18n4k
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.util.*
import kotlinx.atomicfu.TraceBase.None.append
import kotlinx.browser.localStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.dom.get
import org.w3c.files.File

class CreatePostViewModel(private val client: HttpClient) {
    @OptIn(InternalAPI::class)
    suspend fun createPost(dto: PostDTO, file: File): HttpStatusCode {
        val jwt = localStorage.get("JWT")
        val language = i18n4k.locale.language
        lateinit var fileBytes: ByteArray
        lateinit var response: HttpResponse

        CoroutineScope(Dispatchers.Main).launch {
            fileBytes = file.readBytes()
            response = client.post("http://localhost:9090/$language/blog/") {
                headers {
                    append("Authorization", "Bearer $jwt")
                    append(HttpHeaders.ContentType, ContentType.MultiPart.FormData)
//                    append("dto", Json.encodeToString(dto))
                }
//                body = ByteArrayContent(fileBytes, ContentType.Application.OctetStream)
                setBody() {
                    append("file", file.name, Headers.build {
                        ByteArrayContent(fileBytes, ContentType.Application.OctetStream)
                    })
                    append("dto", Json.encodeToString(dto))
                }
            }
        }

        return response.status
    }
}

abstract class FileException(val file: File, message: String) : Throwable("File (${file.name}): $message")
class FileErrorException(file: File) : FileException(file, "read failed")
class FileAbortException(file: File) : FileException(file, "read aborted")