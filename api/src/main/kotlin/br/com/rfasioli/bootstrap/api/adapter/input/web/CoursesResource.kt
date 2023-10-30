package br.com.rfasioli.bootstrap.api.adapter.input.web

import br.com.rfasioli.bootstrap.api.adapter.input.web.mapper.toCourse
import br.com.rfasioli.bootstrap.api.adapter.input.web.mapper.toCourseResourceResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResquest
import br.com.rfasioli.bootstrap.api.adapter.input.web.springdoc.CoursesResourceSpringdoc
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import br.com.rfasioli.bootstrap.api.domain.port.input.CoursesByStageFinder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/courses")
class CoursesResource(
    private val coursesByStageFinder: CoursesByStageFinder,
) : CoursesResourceSpringdoc {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getCoursesByStages(
        @RequestParam stages: List<Stage>,
    ): Flux<CourseResourceResponse> =
        coursesByStageFinder.fetchCoursesByStage(stages)
            .map { it.toCourseResourceResponse() }

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    override fun getCourseById(
        @PathVariable id: String
    ): Mono<CourseResourceResponse> {
        TODO("Not yet implemented")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun postCourse(
        @RequestBody course: CourseResourceResquest,
    ): Mono<CourseResourceResponse> =
        Mono.just(course)
            .map { it.toCourse() }
            .map { it.toCourseResourceResponse() }

    @PutMapping("/id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    override fun putCourseById(
        @PathVariable id: String,
        @RequestBody course: CourseResourceResquest
    ): Mono<CourseResourceResponse> =
        Mono.just(Pair(id, course))
            .map { it.second.toCourse(it.first) }
            .map { it.toCourseResourceResponse() }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    override fun deleteCourseById(
        @PathVariable id: String
    ): Mono<Void> =
        Mono.just(id)
            .map { null }

}
