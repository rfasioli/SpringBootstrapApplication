package br.com.rfasioli.bootstrap.api.domain.usecase.enroll

import br.com.rfasioli.bootstrap.api.UnitTest
import br.com.rfasioli.bootstrap.api.application.exception.EnrollmentNotElegibleException
import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.model.Requirement
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseFetcher
import br.com.rfasioli.bootstrap.api.domain.port.output.enroll.EnrollmentAnalyzer
import br.com.rfasioli.bootstrap.mock.core.domain.buildMock
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.LocalDate

internal class EnrollInElegibleCourseUseCaseTest(
    @MockK private val courseFetcher: CourseFetcher,
    @MockK private val enrollmentAnalyser: EnrollmentAnalyzer,
) : UnitTest() {
    @InjectMockKs
    private lateinit var enrollInElegibleCourseUseCase: EnrollInElegibleCourseUseCase

    @Test
    fun `Should send requirement for analysis when course exists and requirement is elegible`() {
        val course = Course.buildMock()
        val requirement =
            Requirement.buildMock()
                .copy(
                    course = course.id!!,
                    birthDate = LocalDate.now().minusYears(course.stage.age.first.toLong()),
                )

        every { courseFetcher.fetchCourseById(course.id!!) }
            .returns(Mono.just(course))

        every { enrollmentAnalyser.sendRequirementForAnalysis(any()) }
            .returns(Mono.empty())

        StepVerifier.create(enrollInElegibleCourseUseCase.enroll(requirement))
            .expectNextCount(1)
            .then { verify { enrollmentAnalyser.sendRequirementForAnalysis(any()) } }
            .verifyComplete()
    }

    @Test
    fun `Should throws EnrollmentNotElegibleException when requirement not elegible`() {
        val course = Course.buildMock()
        val requirement =
            Requirement.buildMock()
                .copy(
                    course = course.id!!,
                    birthDate = LocalDate.now(),
                )

        every { courseFetcher.fetchCourseById(course.id!!) }
            .returns(Mono.just(course))

        StepVerifier.create(enrollInElegibleCourseUseCase.enroll(requirement))
            .then { verify(exactly = 0) { enrollmentAnalyser.sendRequirementForAnalysis(any()) } }
            .expectError(EnrollmentNotElegibleException::class.java)
            .verify()
    }

    @Test
    fun `Should complete without items when course is not found`() {
        val requirement = Requirement.buildMock()

        every { courseFetcher.fetchCourseById(requirement.course) }
            .returns(Mono.empty())

        StepVerifier.create(enrollInElegibleCourseUseCase.enroll(requirement))
            .verifyComplete()
    }

    @Test
    fun `Should propagate error when error occurs while fetching course`() {
        val requirement = Requirement.buildMock()
        val exception = RuntimeException("Error fetching course")

        every { courseFetcher.fetchCourseById(requirement.course) }
            .returns(Mono.error(exception))

        StepVerifier.create(enrollInElegibleCourseUseCase.enroll(requirement))
            .expectErrorMatches {
                it is RuntimeException && it.message == "Error fetching course"
            }
            .verify()
    }
}
