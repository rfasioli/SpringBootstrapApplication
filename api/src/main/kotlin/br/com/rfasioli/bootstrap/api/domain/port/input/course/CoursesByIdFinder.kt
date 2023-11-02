package br.com.rfasioli.bootstrap.api.domain.port.input.course

import br.com.rfasioli.bootstrap.api.domain.model.Course
import reactor.core.publisher.Mono
import java.util.UUID

fun interface CoursesByIdFinder {
    fun fetchCourseById(id: UUID): Mono<Course>
}
