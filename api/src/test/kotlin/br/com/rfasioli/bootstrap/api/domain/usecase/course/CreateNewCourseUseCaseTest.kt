package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.UnitTest
import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseSaver
import br.com.rfasioli.bootstrap.mock.core.domain.buildMock
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

internal class CreateNewCourseUseCaseTest(
    @MockK private val courseSaver: CourseSaver,
) : UnitTest() {
    @InjectMockKs
    private lateinit var createNewCourseUseCase: CreateNewCourseUseCase

    @Test
    fun `Should return the created course when course is successfully created`() {
        val expectedCourse = Course.buildMock()

        every { courseSaver.saveCourse(expectedCourse) }
            .returns(Mono.just(expectedCourse))

        StepVerifier.create(createNewCourseUseCase.createCourse(expectedCourse))
            .expectNext(expectedCourse)
            .verifyComplete()
    }

    @Test
    fun `Should propagate error when error occurs while creating course`() {
        val courseToCreate = Course.buildMock()
        val exception = RuntimeException("Error creating course")

        every { courseSaver.saveCourse(courseToCreate) }
            .returns(Mono.error(exception))

        StepVerifier.create(createNewCourseUseCase.createCourse(courseToCreate))
            .expectErrorMatches { it is RuntimeException && it.message == "Error creating course" }
            .verify()
    }
}
