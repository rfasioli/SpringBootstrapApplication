package br.com.rfasioli.bootstrap.api.domain.model

import br.com.sda.bootstrap.sdabootstrap.api.core.domain.Gender
import java.time.LocalDate
import java.util.UUID

data class Requirement(
    val name: Name,
    val gender: Gender,
    val birthDate: LocalDate,
    val document: String,
    val course: UUID
) {
    companion object
}
