package br.com.rfasioli.bootstrap.mock.core.domain

import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import br.com.rfasioli.bootstrap.mock.fixtureMoney
import br.com.rfasioli.bootstrap.mock.fixtureStage
import io.github.serpro69.kfaker.faker
import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink
import java.math.BigDecimal
import java.util.UUID

private val faker = faker { }

fun Course.Companion.buildMock(stage: Stage? = null): Course =
    Course(
        id = UUID.randomUUID(),
        name = faker.educator.courseName(),
        description = faker.educator.subject(),
        stage = stage ?: fixtureStage(),
        tuitionFee = BigDecimal(fixtureMoney()),
    )

fun Course.Companion.generateCourse(): Flux<Course> =
    Flux.generate { synchronousSink: SynchronousSink<Course> ->
        synchronousSink.next(
            buildMock(),
        )
    }
