package br.com.rfasioli.bootstrap.api.domain.port.output.course

import br.com.rfasioli.bootstrap.api.domain.model.Course
import reactor.core.publisher.Mono

fun interface CourseSaver {
    fun saveCourse(course: Course): Mono<Course>
}
