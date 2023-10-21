package br.com.rfasioli.bootstrap.api.adapter.output.messaging

import br.com.rfasioli.bootstrap.api.domain.model.EnrollmentAggregation
import br.com.rfasioli.bootstrap.api.domain.port.output.EnrollmentAnalyzer
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class PublishEnrollmentAnalysisAdapter : EnrollmentAnalyzer {
    override fun sendRequirementForAnalysis(courseEnrollmentRequirement: EnrollmentAggregation): Mono<Unit> {
        TODO("Not yet implemented")
    }
}
