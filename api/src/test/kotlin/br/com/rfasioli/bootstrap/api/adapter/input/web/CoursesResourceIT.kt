package br.com.rfasioli.bootstrap.api.adapter.input.web

import br.com.rfasioli.bootstrap.api.IntegrationTest
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResquest
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import br.com.rfasioli.bootstrap.mock.app.adapters.input.web.resources.buildMock
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import reactor.core.publisher.Mono
import java.util.UUID

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

    @Nested
    inner class GetCoursesByIdTests {
        @Test
        fun getCoursesById() {
            val id = UUID.randomUUID()
            webTestClient.get()
                .uri { uriBuilder ->
                    uriBuilder
                        .path("$COURSES_URI/$id")
                        .build()
                }
                .exchange()
                .expectStatus().is5xxServerError
        }
    }

    @Nested
    inner class PostCourseTests {
        @Test
        fun postCourse() {
            val course = CourseResourceResquest.buildMock()

            webTestClient.post()
                .uri { uriBuilder ->
                    uriBuilder
                        .path(COURSES_URI)
                        .build()
                }
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(course), CourseResourceResquest::class.java)
                .exchange()
                .expectStatus().isCreated
                .expectBody(CourseResourceResponse::class.java)
        }
    }

    @Nested
    inner class PutCourseTests {
        @Test
        fun putCourse() {
            val course = CourseResourceResquest.buildMock()
            val id = UUID.randomUUID()

            webTestClient.put()
                .uri { uriBuilder ->
                    uriBuilder
                        .path("$COURSES_URI/$id")
                        .build()
                }
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(course), CourseResourceResquest::class.java)
                .exchange()
                .expectStatus().isAccepted
                .expectBody(CourseResourceResponse::class.java)
        }
    }

    @Nested
    inner class DeleteCourseByIdTests {
        @Test
        fun deleteCoursesById() {
            val id = UUID.randomUUID()
            webTestClient.delete()
                .uri { uriBuilder ->
                    uriBuilder
                        .path("$COURSES_URI/$id")
                        .build()
                }
                .exchange()
                .expectStatus().isNoContent
        }
    }
}
