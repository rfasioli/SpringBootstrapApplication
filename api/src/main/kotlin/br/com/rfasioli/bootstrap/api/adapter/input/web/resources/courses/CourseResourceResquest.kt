package br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses

import br.com.rfasioli.bootstrap.api.domain.model.Stage
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal

data class CourseResourceResquest(
    @NotBlank val name: String,
    @NotBlank val description: String,
    @NotNull val stage: Stage,
    @PositiveOrZero val tuitionFee: BigDecimal,
) {
    companion object
}
