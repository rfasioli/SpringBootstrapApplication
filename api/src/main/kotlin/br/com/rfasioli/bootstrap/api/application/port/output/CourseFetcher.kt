package br.com.rfasioli.bootstrap.api.application.port.output

import br.com.rfasioli.bootstrap.api.domain.model.Course
import reactor.core.publisher.Mono
import java.util.UUID

interface CourseFetcher {
    fun fetchCourseById(id: UUID): Mono<Course>
}
