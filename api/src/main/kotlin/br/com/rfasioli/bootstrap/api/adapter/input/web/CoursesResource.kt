package br.com.rfasioli.bootstrap.api.adapter.input.web

import br.com.rfasioli.bootstrap.api.adapter.input.web.mapper.toCourse
import br.com.rfasioli.bootstrap.api.adapter.input.web.mapper.toCourseResourceResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResponse
import br.com.rfasioli.bootstrap.api.adapter.input.web.resources.courses.CourseResourceResquest
import br.com.rfasioli.bootstrap.api.adapter.input.web.springdoc.CoursesResourceSpringdoc
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CourseCreator
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CourseSecureRemover
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CourseUpdater
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CoursesByIdFinder
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CoursesByStageFinder
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
import java.util.UUID

@RestController
@RequestMapping("/courses")
class CoursesResource(
    private val coursesByStageFinder: CoursesByStageFinder,
    private val coursesByIdFinder: CoursesByIdFinder,
    private val courseCreator: CourseCreator,
    private val courseUpdater: CourseUpdater,
    private val courseSecureRemover: CourseSecureRemover,
) : CoursesResourceSpringdoc {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    override fun getCoursesByStages(
        @RequestParam stages: List<Stage>,
    ): Flux<CourseResourceResponse> =
        coursesByStageFinder.fetchCoursesByStage(stages)
            .map { it.toCourseResourceResponse() }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    override fun getCourseById(
        @PathVariable id: UUID,
    ): Mono<CourseResourceResponse> =
        coursesByIdFinder.fetchCourseById(id)
            .map { it.toCourseResourceResponse() }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun postCourse(
        @RequestBody course: CourseResourceResquest,
    ): Mono<CourseResourceResponse> =
        Mono.just(course.toCourse())
            .flatMap { courseCreator.createCourse(it) }
            .map { it.toCourseResourceResponse() }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    override fun putCourseById(
        @PathVariable id: UUID,
        @RequestBody course: CourseResourceResquest,
    ): Mono<CourseResourceResponse> =
        Mono.just(course.toCourse(id))
            .flatMap { courseUpdater.updateCourse(it) }
            .map { it.toCourseResourceResponse() }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun deleteCourseById(
        @PathVariable id: UUID,
    ): Mono<Unit> =
        courseSecureRemover.deleteCourseById(id)
}
