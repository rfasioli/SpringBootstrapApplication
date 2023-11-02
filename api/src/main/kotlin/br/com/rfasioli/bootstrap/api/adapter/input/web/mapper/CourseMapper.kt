package br.com.rfasioli.bootstrap.api.adapter.input.web.mapper

import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResquest
import br.com.rfasioli.bootstrap.api.application.exception.InconsistentDataException
import br.com.rfasioli.bootstrap.api.domain.model.Course
import java.util.UUID

fun Course.toCourseResourceResponse() =
    CourseResourceResponse(
        id = this.id ?: throw InconsistentDataException("Course.id"),
        name = this.name,
        description = this.description,
        stage = this.stage,
        tuitionFee = this.tuitionFee,
    )

fun CourseResourceResquest.toCourse() =
    Course(
        name = this.name,
        description = this.description,
        stage = this.stage,
        tuitionFee = this.tuitionFee,
    )

fun CourseResourceResquest.toCourse(id: UUID) =
    Course(
        id = id,
        name = this.name,
        description = this.description,
        stage = this.stage,
        tuitionFee = this.tuitionFee,
    )
