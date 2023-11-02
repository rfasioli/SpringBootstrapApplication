package br.com.rfasioli.bootstrap.api.adapter.input.web

import br.com.rfasioli.bootstrap.api.IntegrationTest
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.enroll.PostCourseEnrollRequest
import br.com.rfasioli.bootstrap.mock.app.adapters.input.web.resources.buildMock
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class EnrollmentResourceIT : IntegrationTest() {
    companion object {
        private const val COURSES_ENROLL_URI = "/enroll"
    }

    @Nested
    inner class EnrollTests {
        @Test
        fun enroll() {
            val body = PostCourseEnrollRequest.buildMock()

            webTestClient.post()
                .uri { uriBuilder ->
                    uriBuilder
                        .path(COURSES_ENROLL_URI)
                        .build()
                }
                .bodyValue(body)
                .exchange()
                .expectStatus().is5xxServerError
        }
    }
}
