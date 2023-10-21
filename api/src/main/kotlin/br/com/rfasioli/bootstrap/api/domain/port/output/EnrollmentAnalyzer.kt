package br.com.rfasioli.bootstrap.api.domain.port.output

import br.com.rfasioli.bootstrap.api.domain.model.EnrollmentAggregation
import reactor.core.publisher.Mono

interface EnrollmentAnalyzer {
    fun sendRequirementForAnalysis(courseEnrollmentRequirement: EnrollmentAggregation): Mono<Unit>
}
