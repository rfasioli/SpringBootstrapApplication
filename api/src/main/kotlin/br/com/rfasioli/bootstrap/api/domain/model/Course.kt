package br.com.rfasioli.bootstrap.api.domain.model

import java.math.BigDecimal
import java.util.UUID

data class Course(
    val id: UUID? = UUID.randomUUID(),
    val name: String,
    val description: String,
    val stage: Stage,
    val tuitionFee: BigDecimal
) {
    companion object
}
