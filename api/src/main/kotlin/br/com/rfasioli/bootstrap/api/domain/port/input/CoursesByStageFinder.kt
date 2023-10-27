package br.com.rfasioli.bootstrap.api.domain.port.input

import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import reactor.core.publisher.Flux

fun interface CoursesByStageFinder {
    fun fetchCoursesByStage(stages: List<Stage>): Flux<Course>
}
