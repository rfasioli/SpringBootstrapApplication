package br.com.rfasioli.bootstrap.api.adapter.input.web.mapper

import br.com.sda.bootstrap.sdabootstrap.api.core.domain.Gender

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
