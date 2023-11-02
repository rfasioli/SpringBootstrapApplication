package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.application.exception.CourseNotFoundException
import br.com.rfasioli.bootstrap.api.application.exception.RequiredDataNotProvidedException
import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CourseUpdater
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseFetcher
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseSaver
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class UpdateExistingCourseUseCase(
    private val courseFetcher: CourseFetcher,
    private val courseSaver: CourseSaver,
) : CourseUpdater {
    override fun updateCourse(course: Course): Mono<Course> =
        Mono.just(course.id ?: throw RequiredDataNotProvidedException("Course.id"))
            .flatMap {
                courseFetcher.fetchCourseById(it)
                    .switchIfEmpty(Mono.error(CourseNotFoundException(course.id.toString())))
                    .doOnSuccess { courseSaver.saveCourse(course) }
            }
}
