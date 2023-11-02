package br.com.rfasioli.bootstrap.api.domain.model

import java.util.UUID

data class EnrollmentAggregation(
    val id: UUID,
    val requirement: Requirement,
    val course: Course,
)
