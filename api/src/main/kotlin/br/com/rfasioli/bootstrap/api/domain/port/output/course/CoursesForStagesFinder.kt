package br.com.rfasioli.bootstrap.api.domain.port.output.course

import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import reactor.core.publisher.Flux

fun interface CoursesForStagesFinder {
    fun findCoursesByStage(stages: List<Stage>): Flux<Course>
}
