package br.com.rfasioli.bootstrap.api.adapter.input.web.springdoc

import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResquest
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Tag(name = "Course", description = "this API provide methods for operations with Courses")
interface CoursesResourceSpringdoc {
    @Operation(summary = "Get Courses by Stages")
    fun getCoursesByStages(
        stages: List<Stage>,
    ): Flux<CourseResourceResponse>

    @Operation(summary = "Get Course by Id")
    fun getCourseById(
        id: String,
    ): Mono<CourseResourceResponse>

    @Operation(summary = "Add a Course")
    fun postCourse(
        course: CourseResourceResquest,
    ): Mono<CourseResourceResponse>

    @Operation(summary = "Update a Course")
    fun putCourseById(
        id: String,
        course: CourseResourceResquest,
    ): Mono<CourseResourceResponse>

    @Operation(summary = "Get Courses by Stages")
    fun deleteCourseById(
        id: String,
    ): Mono<Void>
}
