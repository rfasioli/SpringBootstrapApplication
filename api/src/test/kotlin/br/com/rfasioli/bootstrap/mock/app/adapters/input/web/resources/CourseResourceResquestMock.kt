package br.com.rfasioli.bootstrap.mock.app.adapters.input.web.resources

import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResquest
import br.com.rfasioli.bootstrap.mock.fixtureMoney
import br.com.rfasioli.bootstrap.mock.fixtureStage
import io.github.serpro69.kfaker.faker
import java.math.BigDecimal

private val faker = faker { }

fun CourseResourceResquest.Companion.buildMock(): CourseResourceResquest =
    CourseResourceResquest(
        name = faker.educator.courseName(),
        description = faker.educator.subject(),
        stage = fixtureStage(),
        tuitionFee = BigDecimal(fixtureMoney()),
    )
