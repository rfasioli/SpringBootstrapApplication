package br.com.rfasioli.bootstrap.api.domain.port.input.course

import br.com.rfasioli.bootstrap.api.domain.model.Course
import reactor.core.publisher.Mono

fun interface CourseCreator {
    fun createCourse(course: Course): Mono<Course>
}
