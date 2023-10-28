package br.com.rfasioli.bootstrap.api.adapter.input.web.springdoc

import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.GetCourseResourceResponse
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import reactor.core.publisher.Flux

@Tag(name = "Course", description = "this API provide methods for operations with Courses")
fun interface CoursesResourceSpringdoc {
    @Operation(summary = "Get Courses by Stages")
    fun getCoursesByStages(stages: List<Stage>): Flux<GetCourseResourceResponse>

}
