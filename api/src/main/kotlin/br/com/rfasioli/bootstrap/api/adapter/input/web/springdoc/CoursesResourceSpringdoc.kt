package br.com.rfasioli.bootstrap.api.adapter.input.web.springdoc

import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.GetCourseResourceResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.PostCourseEnrollRequest
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.PostCourseEnrollResponse
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Tag(name = "Course", description = "this API provide methods for operations with Courses")
interface CoursesResourceSpringdoc {

    @Operation(summary = "Get Courses by Stages")
    fun getCoursesByStages(stages: List<Stage>): Flux<GetCourseResourceResponse>

    @Operation(summary = "Send an Enrollment Requirement")
    fun enroll(request: PostCourseEnrollRequest): Mono<PostCourseEnrollResponse>
}