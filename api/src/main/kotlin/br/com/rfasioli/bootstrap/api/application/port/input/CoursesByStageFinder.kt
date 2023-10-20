package br.com.rfasioli.bootstrap.api.application.port.input

import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import reactor.core.publisher.Flux

interface CoursesByStageFinder {
    fun fetchCoursesByStage(stages: List<Stage>): Flux<Course>
}
