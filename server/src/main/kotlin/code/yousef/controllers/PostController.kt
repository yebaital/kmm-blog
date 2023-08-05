package code.yousef.controllers

import code.yousef.blog.shared.common.main.models.enums.PostStatus
import code.yousef.blog.shared.common.main.models.enums.PostType
import code.yousef.blog.shared.common.main.models.enums.WrittenLanguage
import code.yousef.models.Post
import code.yousef.services.PostService
import io.smallrye.mutiny.coroutines.awaitSuspending
import jakarta.annotation.security.RolesAllowed
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.jboss.resteasy.reactive.RestResponse

@Path("/{locale}/blog/")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
class PostController(private val postService: PostService) {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    suspend fun createPost(post: Post): Post {
        return postService.savePost(post).awaitSuspending()
    }


    @Path("/{pageNumber}")
    @GET
    suspend fun getPosts(locale: String, pageNumber: Int): RestResponse<List<Post>> {
        val language = if (locale == "ar") WrittenLanguage.ARABIC else WrittenLanguage.ENGLISH
        var page = pageNumber

        if (pageNumber > 0)  page -= 1
        if (pageNumber <= 0) return RestResponse.status<List<Post>>(400)
        val posts = postService.findByStatusAndTypeAndLanguage(
            PostStatus.DRAFT,
            PostType.BLOGPOST,
            language,
            page
        ).awaitSuspending()
        return RestResponse.ok(posts)
    }
}