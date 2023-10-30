package br.com.rfasioli.bootstrap.api.adapter.input.web.mapper

import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResquest
import br.com.rfasioli.bootstrap.api.domain.model.Course
import java.util.UUID

fun Course.toCourseResourceResponse() =
    CourseResourceResponse(
        id = this.id.toString(),
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

fun CourseResourceResquest.toCourse(id: String) =
    Course(
        id = UUID.fromString(id),
        name = this.name,
        description = this.description,
        stage = this.stage,
        tuitionFee = this.tuitionFee,
    )
