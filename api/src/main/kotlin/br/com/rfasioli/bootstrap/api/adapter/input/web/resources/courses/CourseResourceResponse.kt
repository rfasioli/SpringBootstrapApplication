package br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses

import br.com.rfasioli.bootstrap.api.domain.model.Stage
import java.math.BigDecimal
import java.util.UUID

data class CourseResourceResponse(
    val id: UUID,
    val name: String,
    val description: String,
    val stage: Stage,
    val tuitionFee: BigDecimal,
)
