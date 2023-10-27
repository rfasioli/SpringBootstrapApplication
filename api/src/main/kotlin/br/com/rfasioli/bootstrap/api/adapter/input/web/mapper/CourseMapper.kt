package br.com.rfasioli.bootstrap.api.adapter.input.web.mapper

import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.GetCourseResourceResponse
import br.com.rfasioli.bootstrap.api.domain.model.Course

fun Course.toCourseResourceResponse() =
    GetCourseResourceResponse(
        id = this.id.toString(),
        name = this.name,
        description = this.description,
        stage = this.stage,
        tuitionFee = this.tuitionFee,
    )
