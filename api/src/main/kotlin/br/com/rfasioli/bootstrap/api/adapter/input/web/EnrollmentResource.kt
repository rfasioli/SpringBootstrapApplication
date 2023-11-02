package br.com.rfasioli.bootstrap.api.adapter.input.web

import br.com.rfasioli.bootstrap.api.adapter.input.web.mapper.toRequirement
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.enroll.PostCourseEnrollRequest
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.enroll.PostCourseEnrollResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.springdoc.EnrollmentResourceSpringdoc
import br.com.rfasioli.bootstrap.api.domain.port.input.enroll.CourseEnroller
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/enroll")
class EnrollmentResource(
    private val courseEnroller: CourseEnroller,
) : EnrollmentResourceSpringdoc {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun enroll(request: PostCourseEnrollRequest): Mono<PostCourseEnrollResponse> =
        courseEnroller.enroll(request.toRequirement())
            .flatMap { Mono.just(PostCourseEnrollResponse(it)) }
}
