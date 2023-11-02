package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.application.exception.CourseNotFoundException
import br.com.rfasioli.bootstrap.api.application.exception.RequiredDataNotProvidedException
import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CourseUpdater
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseFetcher
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseSaver
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UpdateExistingCourseUseCase(
    private val courseFetcher: CourseFetcher,
    private val courseSaver: CourseSaver,
) : CourseUpdater {
    companion object {
        private const val LOGGER_CATEGORY_UPDATE_COURSE = "UpdateExistingCourseUseCase.updateCourse"
    }

    override fun updateCourse(course: Course): Mono<Course> =
        Mono.just(course.id ?: throw RequiredDataNotProvidedException("Course.id"))
            .flatMap {
                courseFetcher.fetchCourseById(it)
                    .switchIfEmpty(Mono.error(CourseNotFoundException(course.id.toString())))
                    .flatMap { courseSaver.saveCourse(course) }
            }
}
