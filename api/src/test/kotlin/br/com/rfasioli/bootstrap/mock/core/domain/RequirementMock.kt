package br.com.rfasioli.bootstrap.mock.core.domain

import br.com.rfasioli.bootstrap.api.domain.model.Name
import br.com.rfasioli.bootstrap.api.domain.model.Requirement
import br.com.rfasioli.bootstrap.mock.fixtureBirthDate
import br.com.rfasioli.bootstrap.mock.fixtureCpf
import br.com.rfasioli.bootstrap.mock.fixtureGender
import java.util.UUID

fun Requirement.Companion.buildMock(): Requirement =
    Requirement(
        name = Name.buildMock(),
        gender = fixtureGender(),
        birthDate = fixtureBirthDate(),
        document = fixtureCpf(),
        course = UUID.randomUUID()
    )
