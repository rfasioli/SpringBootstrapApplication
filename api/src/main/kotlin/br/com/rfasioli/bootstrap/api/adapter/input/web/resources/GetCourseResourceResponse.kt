package br.com.rfasioli.bootstrap.api.adapter.input.web.resources

import br.com.rfasioli.bootstrap.api.domain.model.Stage
import java.math.BigDecimal

data class GetCourseResourceResponse(
    val id: String,
    val name: String,
    val description: String,
    val stage: Stage,
    val tuitionFee: BigDecimal
)
