package br.com.rfasioli.bootstrap.mock.core.domain

import br.com.rfasioli.bootstrap.api.domain.model.Course
import io.github.serpro69.kfaker.faker
import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink
import java.math.BigDecimal
import java.util.UUID

private val faker = faker { }

fun Course.Companion.buildMock(): Course =
    Course(
        id = UUID.randomUUID(),
        name = faker.rickAndMorty.locations(),
        description = faker.rickAndMorty.quotes(),
        stage = fixtureStage(),
        tuitionFee = BigDecimal(fixtureMoney())
    )

fun Course.Companion.generateCourse(): Flux<Course> =
    Flux.generate { synchronousSink: SynchronousSink<Course> ->
        synchronousSink.next(
            buildMock()
        )
    }
