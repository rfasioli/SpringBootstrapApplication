package br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses

import br.com.rfasioli.bootstrap.api.domain.model.Stage
import java.math.BigDecimal

data class CourseResourceResponse(
    val id: String,
    val name: String,
    val description: String,
    val stage: Stage,
    val tuitionFee: BigDecimal,
)
