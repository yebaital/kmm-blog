package code.yousef.repositories

import code.yousef.models.Project
import io.quarkus.hibernate.reactive.panache.kotlin.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProjectRepository : PanacheRepository<Project> {
    suspend fun findAll(page: Int): Uni<List<Project>> = findAll().list()

    suspend fun findBySlug(slug: String): Uni<Project?> = find("slug", slug).firstResult()
}