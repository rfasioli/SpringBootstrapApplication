package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.application.exception.CourseNotFoundException
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CourseSecureRemover
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseFetcher
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseRemover
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.UUID

@Service
class SecureCourseRemoveUseCase(
    private val courseFetcher: CourseFetcher,
    private val courseRemover: CourseRemover,
) : CourseSecureRemover {
    override fun deleteCourseById(id: UUID): Mono<Unit> =
        courseFetcher.fetchCourseById(id)
            .switchIfEmpty(Mono.error(CourseNotFoundException(id.toString())))
            .flatMap {
                // TODO check requirements to remove course
                courseRemover.removeCourse(it.id!!)
            }
}
