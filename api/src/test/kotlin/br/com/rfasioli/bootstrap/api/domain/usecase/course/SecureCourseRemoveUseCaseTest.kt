package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.UnitTest
import br.com.rfasioli.bootstrap.api.application.exception.CourseNotFoundException
import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseFetcher
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseRemover
import br.com.rfasioli.bootstrap.mock.core.domain.buildMock
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.UUID

class SecureCourseRemoveUseCaseTest(
    @MockK private val courseFetcher: CourseFetcher,
    @MockK private val courseRemover: CourseRemover,
) : UnitTest() {
    @InjectMockKs
    private lateinit var secureCourseRemoveUseCase: SecureCourseRemoveUseCase

    @Test
    fun `should delete course when it registered`() {
        val registeredCourse = Course.buildMock()

        every { courseFetcher.fetchCourseById(registeredCourse.id!!) }
            .returns(Mono.just(registeredCourse))

        every { courseRemover.removeCourse(registeredCourse.id!!) }
            .returns(Mono.just(Unit))

        StepVerifier.create(secureCourseRemoveUseCase.deleteCourseById(registeredCourse.id!!))
            .expectNextCount(1)
            .then { verify { courseFetcher.fetchCourseById(registeredCourse.id!!) } }
            .then { verify { courseRemover.removeCourse(registeredCourse.id!!) } }
            .verifyComplete()
    }

    @Test
    fun `when course not registered yet must throws CourseNotFoundException`() {
        val id = UUID.randomUUID()

        every { courseFetcher.fetchCourseById(id) }
            .returns(Mono.empty())

        StepVerifier.create(secureCourseRemoveUseCase.deleteCourseById(id))
            .then { verify { courseFetcher.fetchCourseById(any()) } }
            .then { verify(exactly = 0) { courseRemover.removeCourse(any()) } }
            .expectError(CourseNotFoundException::class.java)
    }
}
