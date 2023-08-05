package code.yousef.services

import code.yousef.repositories.ProjectRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProjectService(val projectRepository: ProjectRepository) {

    suspend fun findAll(page: Int) = projectRepository.findAll(page)

    suspend fun findBySlug(slug: String) = projectRepository.findBySlug(slug)

}