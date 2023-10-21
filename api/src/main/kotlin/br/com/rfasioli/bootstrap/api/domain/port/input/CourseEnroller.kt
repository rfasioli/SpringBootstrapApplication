package br.com.rfasioli.bootstrap.api.domain.port.input

import br.com.rfasioli.bootstrap.api.domain.model.Requirement
import reactor.core.publisher.Mono
import java.util.UUID

interface CourseEnroller {
    fun enroll(requirement: Requirement): Mono<UUID>
}
