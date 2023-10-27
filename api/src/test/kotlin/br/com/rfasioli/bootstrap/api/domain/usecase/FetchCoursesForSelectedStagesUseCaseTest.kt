package br.com.rfasioli.bootstrap.api.domain.usecase

import br.com.rfasioli.bootstrap.api.UnitTest
import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import br.com.rfasioli.bootstrap.api.domain.port.output.CoursesFinder
import br.com.rfasioli.bootstrap.mock.core.domain.buildMock
import br.com.rfasioli.bootstrap.mock.core.domain.generateCourse
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import kotlin.random.Random

internal class FetchCoursesForSelectedStagesUseCaseTest(
    @MockK private val coursesFinder: CoursesFinder,
) : UnitTest() {

    @InjectMockKs
    private lateinit var fetchCoursesForSelectedStagesUseCase: FetchCoursesForSelectedStagesUseCase

    @ParameterizedTest
    @EnumSource(Stage::class)
    fun `Should return only one course that matches for corresponding stage`(
        stage: Stage,
    ) {
        val stages = listOf(stage)
        val expected = Course.buildMock()

        every { coursesFinder.findCoursesByStage(stages) }
            .returns(Flux.just(expected))

        StepVerifier.create(fetchCoursesForSelectedStagesUseCase.fetchCoursesByStage(stages))
            .expectNext(expected)
            .verifyComplete()
    }

    @ParameterizedTest
    @EnumSource(Stage::class)
    fun `Should return courses fetched for corresponding stages`(
        stage: Stage,
    ) {
        val stages = listOf(stage)
        val expectedQuantity = Random.nextLong(until = 100)

        every { coursesFinder.findCoursesByStage(stages) }
            .returns(Course.generateCourse().take(expectedQuantity))

        StepVerifier.create(fetchCoursesForSelectedStagesUseCase.fetchCoursesByStage(stages))
            .expectNextCount(expectedQuantity)
            .verifyComplete()
    }
}
