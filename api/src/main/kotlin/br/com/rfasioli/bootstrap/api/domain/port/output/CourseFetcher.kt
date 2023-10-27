package br.com.rfasioli.bootstrap.api.domain.port.output

import br.com.rfasioli.bootstrap.api.domain.model.Course
import reactor.core.publisher.Mono
import java.util.UUID

fun interface CourseFetcher {
    fun fetchCourseById(id: UUID): Mono<Course>
}
