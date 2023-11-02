package br.com.rfasioli.bootstrap.api.application.exception

class CourseNotFoundException : NotFoundException {
    companion object {
        private const val MESSAGE = "Course %s not found."
    }
    constructor(courseIdentifier: String) : super(String.format(MESSAGE, courseIdentifier))
    constructor(courseIdentifier: String, cause: Throwable?) : super(String.format(MESSAGE, courseIdentifier), cause)
}
