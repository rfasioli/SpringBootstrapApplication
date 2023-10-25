package br.com.rfasioli.bootstrap.api.adapter.input.web

import br.com.rfasioli.bootstrap.api.adapter.input.web.mapper.toCourseResourceResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.mapper.toRequirement
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.GetCourseResourceResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.PostCourseEnrollRequest
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.PostCourseEnrollResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.springdoc.CoursesResourceSpringdoc
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import br.com.rfasioli.bootstrap.api.domain.port.input.CourseEnroller
import br.com.rfasioli.bootstrap.api.domain.port.input.CoursesByStageFinder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/courses")
class CoursesResource(
    private val courseEnroller: CourseEnroller,
    private val coursesByStageFinder: CoursesByStageFinder
) : CoursesResourceSpringdoc {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getCoursesByStages(
        @RequestParam stages: List<Stage>
    ): Flux<GetCourseResourceResponse> =
        coursesByStageFinder.fetchCoursesByStage(stages)
            .map { it.toCourseResourceResponse() }

    @PostMapping("/enroll")
    @ResponseStatus(HttpStatus.CREATED)
    override fun enroll(request: PostCourseEnrollRequest): Mono<PostCourseEnrollResponse> =
        courseEnroller.enroll(request.toRequirement())
            .flatMap { Mono.just(PostCourseEnrollResponse(it)) }
}
