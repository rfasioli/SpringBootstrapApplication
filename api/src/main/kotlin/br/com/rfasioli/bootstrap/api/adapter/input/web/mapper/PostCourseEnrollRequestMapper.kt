package br.com.rfasioli.bootstrap.api.adapter.input.web.mapper

import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.PostCourseEnrollRequest
import br.com.rfasioli.bootstrap.api.domain.model.Name
import br.com.rfasioli.bootstrap.api.domain.model.Requirement
import java.util.UUID

fun PostCourseEnrollRequest.toRequirement() =
    Requirement(
        name = Name.splitFromString(this.name),
        gender = genderFromString(this.gender),
        birthDate = this.birthDate,
        document = this.document,
        course = UUID.fromString(this.course),
    )
