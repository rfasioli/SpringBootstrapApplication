package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.UnitTest
import br.com.rfasioli.bootstrap.api.application.exception.CourseNotFoundException
import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseFetcher
import br.com.rfasioli.bootstrap.mock.core.domain.buildMock
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.UUID.randomUUID

class FindCourseByIdUseCaseTest(
    @MockK val courseFetcher: CourseFetcher,
) : UnitTest() {
    @InjectMockKs
    private lateinit var findCourseByIdUseCase: FindCourseByIdUseCase

    @Test
    fun `Should return course by respective registered id`() {
        val expectedCourse = Course.buildMock()

        every { courseFetcher.fetchCourseById(expectedCourse.id!!) }
            .returns(Mono.just(expectedCourse))

        StepVerifier.create(findCourseByIdUseCase.fetchCourseById(expectedCourse.id!!))
            .expectNext(expectedCourse)
            .then { verify { courseFetcher.fetchCourseById(expectedCourse.id!!) } }
            .verifyComplete()
    }

    @Test
    fun `Should throw CourseNotFoundException when course is not found`() {
        val nonExistentCourseId = randomUUID()

        every { courseFetcher.fetchCourseById(nonExistentCourseId) }
            .returns(Mono.empty())

        StepVerifier.create(findCourseByIdUseCase.fetchCourseById(nonExistentCourseId))
            .expectErrorMatches {
                it is CourseNotFoundException &&
                    it.message?.contains(nonExistentCourseId.toString()) ?: false
            }
            .verify()
    }
}
