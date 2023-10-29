package br.com.rfasioli.bootstrap.api.adapter.input.web

import br.com.rfasioli.bootstrap.api.IntegrationTest
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CoursesResourceIT : IntegrationTest() {
    companion object {
        private const val COURSES_URI = "/courses"
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
}
