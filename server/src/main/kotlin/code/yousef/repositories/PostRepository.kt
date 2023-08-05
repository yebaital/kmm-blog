package code.yousef.repositories

import code.yousef.blog.shared.common.main.models.enums.Category
import code.yousef.blog.shared.common.main.models.enums.PostStatus
import code.yousef.blog.shared.common.main.models.enums.PostType
import code.yousef.blog.shared.common.main.models.enums.WrittenLanguage
import code.yousef.models.Post
import code.yousef.utilities.slugify
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheQuery
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepositoryBase
import io.quarkus.panache.common.Page
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.Instant

@ApplicationScoped
@WithSession
public class PostRepository : PanacheRepositoryBase<Post, String> {

    suspend fun findPostById(id: String): Uni<Post> =
        findById(id) ?: throw Exception("Post with id $id not found")

    @Transactional
    suspend fun savePost(post: Post): Uni<Post> {
        post.createdOn = Instant.now()

        return persistAndFlush(post)
    }

    @Transactional
    suspend fun updatePost(id: String, updatedPost: Post): Uni<Post?>? {
        return findPostById(id).onItem().ifNotNull().invoke { post ->
            post!!.apply {
                header = updatedPost.header
                postTitle = updatedPost.postTitle
                postSlug = updatedPost.postTitle?.slugify()
                summary = updatedPost.summary
                body = updatedPost.body
                updatedOn = Instant.now()
                status = updatedPost.status
                type = updatedPost.type
                categories = updatedPost.categories
                hasBeenUpdated = true
                writtenLanguage = updatedPost.writtenLanguage
                programmingLanguage = updatedPost.programmingLanguage
            }
        }
    }

    @Transactional
    suspend fun deletePost(id: String): Uni<Post?>? {
        return findPostById(id).onItem().ifNotNull().invoke { post ->
            delete(post!!)
        }
    }

    suspend fun findByType(type: PostType, page: Int): Uni<List<Post>> {
        val posts: PanacheQuery<Post> = find("type = ?1", type)
        return posts.page(Page.of(page, 10)).list()
    }

    suspend fun findByPostSlug(slug: String): Uni<Post?> = find("slug = ?1", slug).firstResult()

    suspend fun findByCategory(category: Category, page: Int): Uni<List<Post>> {
        val posts: PanacheQuery<Post> = find("category = ?1", category)
        return posts.page(Page.of(page, 10)).list()
    }

    suspend fun findByStatusAndTypeAndLanguage(
        status: PostStatus,
        type: PostType,
        language: WrittenLanguage,
        page: Int
    ): Uni<List<Post>> {
        val posts: PanacheQuery<Post> =
            find("type = ?1 and status = ?2 and writtenLanguage = ?3", type, status, language)
        return posts
            .page(Page.of(page, 10))
            .list()
    }

    suspend fun findByStatusAndTypeAndCreatedOnBetween(
        status: PostStatus,
        type: PostType,
        startDate: Instant,
        endDate: Instant,
        page: Int
    ): Uni<List<Post>> {
//        val posts: PanacheQuery<Post> = postRepository.find(
//            "select posts from Post post where post.status = ?1 and post.type = ?2 post.createdon between ?3 and ?4 and ",
//            status,
//            type,
//            startDate,
//            endDate
//        )
        val posts: PanacheQuery<Post> = find(
            "status = ?1 and type = ?2 and createdOn BETWEEN  ?3 and ?4 and ",
            status,
            type,
            startDate,
            endDate
        )
        return posts.page(Page.of(page, 10)).list()
    }

    suspend fun findByCategoryAndStatusAndTypeAndWrittenLanguage(
        category: Category,
        status: PostStatus,
        type: PostType,
        writtenLanguage: WrittenLanguage,
        page: Int
    ): Uni<List<Post>> {
        val posts: PanacheQuery<Post> = find(
            "category=?1 and status=?2 and type=?3 and writtenLanguage=?4",
            category,
            status,
            type,
            writtenLanguage
        )
        return posts.page(Page.of(page, 10)).list()
    }
}