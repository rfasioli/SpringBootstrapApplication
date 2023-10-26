package br.com.rfasioli.bootstrap.api.adapter.input.web

import br.com.rfasioli.bootstrap.api.IntegrationTest
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.PostCourseEnrollRequest
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import br.com.rfasioli.bootstrap.mock.app.adapters.input.web.resources.buildMock
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CoursesResourceIT : IntegrationTest() {
    companion object {
        private const val COURSES_URI = "/courses/"
        private const val COURSES_ENROLL_URI = "/courses/enroll"
    }

    @Nested
    inner class GetCoursesByStagesTests {
        @Test
        fun getCoursesByStages() {
            webTestClient.get()
                .uri { uriBuilder ->
                    uriBuilder
                        .path(COURSES_URI)
                        .queryParam("stages", Stage.BERCARIO.name)
                        .build()
                }
                .exchange()
                .expectStatus().is5xxServerError
        }
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
