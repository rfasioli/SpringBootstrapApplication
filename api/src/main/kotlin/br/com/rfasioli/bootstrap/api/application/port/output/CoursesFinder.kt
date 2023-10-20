package br.com.rfasioli.bootstrap.api.application.port.output

import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import reactor.core.publisher.Flux

interface CoursesFinder {
    fun findCoursesByStage(stages: List<Stage>): Flux<Course>
}
