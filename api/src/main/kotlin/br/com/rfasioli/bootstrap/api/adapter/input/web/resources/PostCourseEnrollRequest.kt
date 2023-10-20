package br.com.rfasioli.bootstrap.api.adapter.input.web.resources

import java.time.LocalDate

data class PostCourseEnrollRequest(
    val name: String,
    val gender: String,
    val birthDate: LocalDate,
    val document: String,
    val course: String
) {
    companion object
}
