package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.UnitTest
import br.com.rfasioli.bootstrap.api.application.exception.CourseNotFoundException
import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseFetcher
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseSaver
import br.com.rfasioli.bootstrap.mock.core.domain.buildMock
import io.github.serpro69.kfaker.faker
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

private val faker = faker { }

class UpdateExistingCourseUseCaseTest(
    @MockK private val courseFetcher: CourseFetcher,
    @MockK private val courseSaver: CourseSaver,
) : UnitTest() {

    @InjectMockKs
    private lateinit var updateExistingCourseUseCase: UpdateExistingCourseUseCase

    @Test
    fun `should update course when it already registered`() {
        val registeredCourse = Course.buildMock()
        val updatedCourse = registeredCourse
            .copy(name = faker.educator.courseName())

        every { courseFetcher.fetchCourseById(updatedCourse.id!!) }
            .returns(Mono.just(registeredCourse))

        every { courseSaver.saveCourse(updatedCourse) }
            .returns(Mono.just(updatedCourse))

        StepVerifier.create(updateExistingCourseUseCase.updateCourse(updatedCourse))
            .expectNextCount(1)
            .then { verify { courseFetcher.fetchCourseById(any()) } }
            .then { verify { courseSaver.saveCourse(any()) } }
            .verifyComplete()
    }

    @Test
    fun `when course not registered yet must throws CourseNotFoundException`() {
        val updatedCourse = Course.buildMock()

        StepVerifier.create(updateExistingCourseUseCase.updateCourse(updatedCourse))
            .then { verify { courseFetcher.fetchCourseById(any()) } }
            .then { verify(exactly = 0) { courseSaver.saveCourse(any()) } }
            .expectError(CourseNotFoundException::class.java)
    }
}
