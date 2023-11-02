package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CoursesByStageFinder
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CoursesForStagesFinder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.SignalType
import java.util.logging.Level

@Service
class FetchCoursesForSelectedStagesUseCase(
    private val coursesForStagesFinder: CoursesForStagesFinder,
) : CoursesByStageFinder {
    companion object {
        private const val LOGGER_CATEGORY_FETCH_COURSES_BY_STAGE = "FetchCoursesForSelectedStages.fetchCoursesByStage"
    }

    override fun fetchCoursesByStage(stages: List<Stage>): Flux<Course> =
        coursesForStagesFinder.findCoursesByStage(stages)
            .log(LOGGER_CATEGORY_FETCH_COURSES_BY_STAGE, Level.FINE, SignalType.ON_NEXT)
            .log(LOGGER_CATEGORY_FETCH_COURSES_BY_STAGE, Level.INFO, SignalType.ON_COMPLETE)
            .log(LOGGER_CATEGORY_FETCH_COURSES_BY_STAGE, Level.SEVERE, SignalType.ON_ERROR)
}
