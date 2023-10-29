package br.com.rfasioli.bootstrap.api.adapter.input.web.springdoc

import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.PostCourseEnrollRequest
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.PostCourseEnrollResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import reactor.core.publisher.Mono

@Tag(name = "Enrollment", description = "this API provide methods for enrollment operations")
fun interface EnrollmentResourceSpringdoc {
    @Operation(summary = "Send an Enrollment Requirement")
    fun enroll(request: PostCourseEnrollRequest): Mono<PostCourseEnrollResponse>
}
