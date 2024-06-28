package br.com.rfasioli.bootstrap.api.domain.usecase.course

import br.com.rfasioli.bootstrap.api.domain.model.Course
import br.com.rfasioli.bootstrap.api.domain.port.input.course.CourseCreator
import br.com.rfasioli.bootstrap.api.domain.port.output.course.CourseSaver
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CreateNewCourseUseCase(
    private val courseSaver: CourseSaver,
) : CourseCreator {
    override fun createCourse(course: Course): Mono<Course> = courseSaver.saveCourse(course)
}
