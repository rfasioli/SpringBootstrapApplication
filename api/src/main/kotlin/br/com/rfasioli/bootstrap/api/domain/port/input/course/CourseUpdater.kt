package br.com.rfasioli.bootstrap.api.domain.port.input.course

import br.com.rfasioli.bootstrap.api.domain.model.Course
import reactor.core.publisher.Mono

fun interface CourseUpdater {
    fun updateCourse(course: Course): Mono<Course>
}
