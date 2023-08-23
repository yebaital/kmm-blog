package code.yousef.controllers

import code.yousef.blog.shared.common.main.models.enums.PostStatus
import code.yousef.blog.shared.common.main.models.enums.PostType
import code.yousef.blog.shared.common.main.models.enums.WrittenLanguage
import code.yousef.models.Post
import code.yousef.models.dto.PostDTO
import code.yousef.services.AppUserService
import code.yousef.services.PostService
import io.smallrye.mutiny.coroutines.awaitSuspending
import jakarta.annotation.security.RolesAllowed
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.*
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.SecurityContext
import org.jboss.resteasy.reactive.RestResponse

@Path("/{locale}/blog/")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
class PostController(private val postService: PostService, private val userService: AppUserService) {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    suspend fun createPost(@Context ctx: SecurityContext, dto: PostDTO): Post {

        // TODO: Add image uploads

        val username = ctx.userPrincipal.name
        val user = userService.findByUsername(username).awaitSuspending()
        val post = Post.fromDTO(dto)
        user?.let { post.author = it}
        return postService.savePost(post).awaitSuspending()
    }


    @Path("/{pageNumber}")
    @GET
    suspend fun getPosts(locale: String, pageNumber: Int): RestResponse<List<Post>> {
        val language = if (locale == "ar") WrittenLanguage.ARABIC else WrittenLanguage.ENGLISH
        var page = pageNumber

        if (pageNumber > 0) page -= 1
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