package br.com.rfasioli.bootstrap.api.adapter.input.web.resources.enroll

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import java.time.LocalDate

data class PostCourseEnrollRequest(
    @NotBlank val name: String,
    @NotBlank val gender: String,
    @Past val birthDate: LocalDate,
    @NotBlank val document: String,
    @NotBlank val course: String,
) {
    companion object
}
