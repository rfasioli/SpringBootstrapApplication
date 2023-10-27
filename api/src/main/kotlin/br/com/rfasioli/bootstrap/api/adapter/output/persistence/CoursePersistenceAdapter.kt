package br.com.rfasioli.bootstrap.api.adapter.output.persistence

import br.com.rfasioli.bootstrap.api.adapter.output.persistence.mapper.toCourse
import br.com.rfasioli.bootstrap.api.adapter.output.persistence.repository.CourseRepository
import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import br.com.rfasioli.bootstrap.api.domain.port.output.CourseFetcher
import br.com.rfasioli.bootstrap.api.domain.port.output.CoursesFinder
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@Component
class CoursePersistenceAdapter(
    private val courseRepository: CourseRepository,
) : CoursesFinder, CourseFetcher {
    override fun findCoursesByStage(stages: List<Stage>): Flux<Course> =
        courseRepository.findByStageIn(stages)
            .map { it.toCourse() }

    override fun fetchCourseById(id: UUID): Mono<Course> =
        courseRepository.findById(id)
            .map { it.toCourse() }
}
