package br.com.rfasioli.bootstrap.api.adapter.output.persistence

import br.com.rfasioli.bootstrap.api.adapter.output.persistence.mapper.toCourse
import br.com.rfasioli.bootstrap.api.adapter.output.persistence.mapper.toCourseEntity
import br.com.rfasioli.bootstrap.api.adapter.output.persistence.repository.CourseRepository
import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseFetcher
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseRemover
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseSaver
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CoursesForStagesFinder
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@Component
class CoursePersistenceAdapter(
    private val courseRepository: CourseRepository,
) : CoursesForStagesFinder, CourseFetcher, CourseSaver, CourseRemover {

    companion object {
        private const val LOGGER_CATEGORY_FIND_COURSE_BY_STAGE = "CoursePersistenceAdapter.findCoursesByStage"
        private const val LOGGER_CATEGORY_FETCH_COURSEBY_ID = "CoursePersistenceAdapter.fetchCourseById"
        private const val LOGGER_CATEGORY_SAVE_COURSE = "CoursePersistenceAdapter.saveCourse"
        private const val LOGGER_CATEGORY_REMOVE_COURSE = "CoursePersistenceAdapter.removeCourse"
    }

    override fun findCoursesByStage(stages: List<Stage>): Flux<Course> =
        courseRepository.findByStageIn(stages)
            .map { it.toCourse() }

    override fun fetchCourseById(id: UUID): Mono<Course> =
        courseRepository.findById(id)
            .map { it.toCourse() }

    override fun saveCourse(course: Course): Mono<Course> =
        Mono.just(course.toCourseEntity())
            .flatMap { courseRepository.save(it) }
            .map { it.toCourse() }

    override fun removeCourse(id: UUID): Mono<Unit> =
        courseRepository.deleteById(id)
            .map { Unit }
}
