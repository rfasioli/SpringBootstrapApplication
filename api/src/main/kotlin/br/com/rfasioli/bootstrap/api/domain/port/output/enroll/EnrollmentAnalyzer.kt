package br.com.rfasioli.bootstrap.api.domain.port.output.enroll

import br.com.rfasioli.bootstrap.api.domain.model.EnrollmentAggregation
import reactor.core.publisher.Mono

fun interface EnrollmentAnalyzer {
    fun sendRequirementForAnalysis(courseEnrollmentRequirement: EnrollmentAggregation): Mono<Unit>
}
