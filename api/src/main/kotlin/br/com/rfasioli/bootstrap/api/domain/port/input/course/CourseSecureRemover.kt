package br.com.rfasioli.bootstrap.api.domain.port.input.course

import reactor.core.publisher.Mono
import java.util.UUID

fun interface CourseSecureRemover {
    fun deleteCourseById(id: UUID): Mono<Unit>
}
