package br.com.rfasioli.bootstrap.api.domain.port.output.course

import reactor.core.publisher.Mono
import java.util.UUID

fun interface CourseRemover {
    fun removeCourse(id: UUID): Mono<Unit>
}
