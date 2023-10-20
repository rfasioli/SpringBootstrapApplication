package br.com.rfasioli.bootstrap.api.domain.model

data class Name(
    val firstName: String,
    val middleName: String? = null,
    val lastName: String
) {
    companion object
}
