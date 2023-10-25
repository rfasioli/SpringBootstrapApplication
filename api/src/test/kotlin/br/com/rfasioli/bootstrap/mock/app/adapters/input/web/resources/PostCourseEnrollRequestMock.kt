package br.com.rfasioli.bootstrap.mock.app.adapters.input.web.resources

import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.PostCourseEnrollRequest
import br.com.rfasioli.bootstrap.mock.fixtureBirthDate
import br.com.rfasioli.bootstrap.mock.fixtureCpf
import br.com.rfasioli.bootstrap.mock.fixtureGender
import br.com.rfasioli.bootstrap.mock.fixtureUuid
import io.github.serpro69.kfaker.faker

private val faker = faker { }

fun PostCourseEnrollRequest.Companion.buildMock(): PostCourseEnrollRequest =
    PostCourseEnrollRequest(
        name = faker.rickAndMorty.characters(),
        gender = fixtureGender().name,
        birthDate = fixtureBirthDate(2),
        document = fixtureCpf(),
        course = fixtureUuid()
    )
