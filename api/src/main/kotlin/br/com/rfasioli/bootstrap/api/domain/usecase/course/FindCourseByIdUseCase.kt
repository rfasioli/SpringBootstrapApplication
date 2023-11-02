package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CoursesByIdFinder
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseFetcher
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

@Service
class FindCourseByIdUseCase(
    private val courseFetcher: CourseFetcher,
) : CoursesByIdFinder {

    override fun fetchCourseById(id: UUID): Mono<Course> =
        courseFetcher.fetchCourseById(id)
}
