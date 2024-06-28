package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.UnitTest
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
            .expectNextCount(1)
            .then { verify { courseFetcher.fetchCourseById(expectedCourse.id!!) } }
            .verifyComplete()
    }
}
