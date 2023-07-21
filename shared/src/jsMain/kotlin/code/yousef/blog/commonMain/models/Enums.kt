package code.yousef.blog.models

enum class PostType {
    BLOGPOST, SNIPPET
}

enum class PostStatus {
    DRAFT, PUBLISHED
}

enum class WrittenLanguage {
    ENGLISH, ARABIC
}

enum class ProgrammingLanguage {
    KOTLIN, PYTHON, JAVASCRIPT, GO, RUST, CSHARP, CPLUSPLUS
}

enum class RoleName {
    ADMIN,
    WRITER,
    EDITOR,
    READER
}