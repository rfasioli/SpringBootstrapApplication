package br.com.rfasioli.bootstrap.api.adapter.input.web.mapper

import br.com.rfasioli.bootstrap.api.domain.model.Gender

fun genderFromString(source: String?) =
    source
        ?.takeIf { it.isNotBlank() }
        ?.let {
            try {
                Gender.valueOf(it)
            } catch (exception: IllegalArgumentException) {
                Gender.OTHER
            }
        }
        ?: Gender.UNINFORMED
