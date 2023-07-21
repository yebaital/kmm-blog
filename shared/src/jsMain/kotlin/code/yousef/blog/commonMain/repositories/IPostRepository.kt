package code.yousef.blog.repositories

import code.yousef.blog.models.*
import kotlinx.datetime.LocalDateTime

interface IPostRepository {
    fun findByStatusAndTypeAndWrittenLanguage(
        status: PostStatus, type: PostType, language: WrittenLanguage,
    ): List<IPost>

    fun findByStatusAndTypeAndCreatedOnBetween(
        status: PostStatus,
        type: PostType,
        startDate: LocalDateTime,
        endDate: LocalDateTime,
    ): List<IPost>

    fun findByType(type: PostType): List<IPost>
    fun findByPostSlug(postSlug: String): IPost
    fun findAllByCategoryAndStatusAndTypeAndWrittenLanguage(
        category: ICategory,
        status: PostStatus,
        type: PostType,
        language: WrittenLanguage,
    ): List<IPost>

    fun findPagedBySubcategoriesAndStatusAndTypeAndWrittenLanguage(
        subcategory: ISubcategory,
        status: PostStatus,
        type: PostType,
        language: WrittenLanguage,
    ): List<IPost>

    fun findAllBySubcategoriesAndStatusAndTypeAndWrittenLanguage(
        subcategory: ISubcategory,
        status: PostStatus,
        type: PostType,
        language: WrittenLanguage
    ): List<IPost>

    fun findAllByStatusAndTypeAndWrittenLanguageAndBodyContainingIgnoreCase(
        status: PostStatus,
        type: PostType,
        language: WrittenLanguage,
        containing: String,
    ): List<IPost>

    fun findAllByStatusAndTypeAndWrittenLanguageAndPostTitleContainingIgnoreCase(
        status: PostStatus,
        type: PostType,
        language: WrittenLanguage,
        containing: String,
    ): List<IPost>
}