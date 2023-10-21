package br.com.rfasioli.bootstrap.api.adapter.output.persistence.mapper

import br.com.rfasioli.bootstrap.api.adapter.output.persistence.entity.CourseEntity
import br.com.rfasioli.bootstrap.api.domain.model.Course

fun CourseEntity.toCourse(): Course =
    Course(
        id = this.id,
        name = this.name,
        description = this.description,
        stage = this.stage,
        tuitionFee = this.tuitionFee
    )
