package code.yousef.services

import code.yousef.blog.shared.common.main.models.enums.Category
import code.yousef.blog.shared.common.main.models.enums.PostStatus
import code.yousef.blog.shared.common.main.models.enums.PostType
import code.yousef.blog.shared.common.main.models.enums.WrittenLanguage
import code.yousef.models.Post
import code.yousef.repositories.PostRepository
import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.time.Instant

@ApplicationScoped
@WithSession
class PostService(private val postRepository: PostRepository) {

    suspend fun findPostById(id: String) = postRepository.findPostById(id)

    @Transactional
    suspend fun savePost(post: Post): Uni<Post> {
        return this.postRepository.savePost(post)
    }

    suspend fun updatePost(id: String, post: Post) = postRepository.updatePost(id, post)

    suspend fun deletePost(id: String) = postRepository.deletePost(id)

    suspend fun findByType(type: PostType, page: Int) = postRepository.findByType(type, page)

    suspend fun findByPostSlug(slug: String) = postRepository.findByPostSlug(slug)

    suspend fun findByCategory(category: Category, page: Int) = postRepository.findByCategory(category, page)

    suspend fun findByStatusAndTypeAndLanguage(
        status: PostStatus,
        type: PostType,
        language: WrittenLanguage,
        page: Int
    ) = postRepository.findByStatusAndTypeAndLanguage(status, type, language, page)

    suspend fun findByStatusAndTypeAndCreatedOnBetween(
        status: PostStatus,
        type: PostType,
        startDate: Instant,
        endDate: Instant,
        page: Int
    ) = postRepository.findByStatusAndTypeAndCreatedOnBetween(status, type, startDate, endDate, page)

    suspend fun findByCategoryAndStatusAndTypeAndWrittenLanguage(
        category: Category,
        status: PostStatus,
        type: PostType,
        writtenLanguage: WrittenLanguage,
        page: Int
    ) = postRepository.findByCategoryAndStatusAndTypeAndWrittenLanguage(category, status, type, writtenLanguage, page)
}