package br.com.rfasioli.bootstrap.mock.core.domain

import br.com.rfasioli.bootstrap.api.domain.model.Name
import io.github.serpro69.kfaker.faker

private val faker = faker { }

fun Name.Companion.buildMock(): Name =
    Name(
        faker.name.firstName(),
        faker.name.nameWithMiddle(),
        faker.name.lastName(),
    )
