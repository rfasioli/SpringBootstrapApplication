package br.com.rfasioli.bootstrap.mock

import br.com.rfasioli.bootstrap.api.domain.model.Gender
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import com.appmattus.kotlinfixture.config.regexToRandom
import com.appmattus.kotlinfixture.kotlinFixture
import java.time.LocalDate
import kotlin.random.Random

private val fixture = kotlinFixture()

private val uuidRegex = "[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}".toRegex()

private val moneyRegex = "[0-10-3][0-9]{3}\\.[0-9]{2}".toRegex()

private val cpfRegex = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}".toRegex()

fun fixtureUuid(): String =
    fixture<String> {
        factory<String> { regexToRandom(uuidRegex) }
    }

fun fixtureMoney(): String =
    fixture<String> {
        factory<String> { regexToRandom(moneyRegex) }
    }

fun fixtureCpf(): String =
    fixture<String> {
        factory<String> { regexToRandom(cpfRegex) }
    }

fun fixtureGender(): Gender =
    fixture.asSequence<Gender>().iterator().next()

fun fixtureStage(): Stage =
    fixture.asSequence<Stage>().iterator().next()

fun fixtureBirthDate(age: Long? = null): LocalDate =
    LocalDate.now().minusYears(age ?: Random.nextLong(2, 19))
