package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CoursesByStageFinder
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CoursesForStagesFinder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.SignalType.ON_COMPLETE
import reactor.core.publisher.SignalType.ON_ERROR
import reactor.core.publisher.SignalType.ON_NEXT
import java.util.logging.Level.INFO

@Service
class FetchCoursesForSelectedStagesUseCase(
    private val coursesForStagesFinder: CoursesForStagesFinder,
) : CoursesByStageFinder {
    companion object {
        private const val LOGGER_CATEGORY_FETCH_COURSES_BY_STAGE = "FetchCoursesForSelectedStages.fetchCoursesByStage"
    }

    override fun fetchCoursesByStage(stages: List<Stage>): Flux<Course> =
        coursesForStagesFinder.findCoursesByStage(stages)
            .log(LOGGER_CATEGORY_FETCH_COURSES_BY_STAGE, INFO, ON_COMPLETE, ON_NEXT, ON_ERROR)
}
