package br.com.rfasioli.bootstrap.api.application.exception

class RequiredDataNotProvidedException : InvalidDataException {
    companion object {
        private const val MESSAGE = "The Required data %s was not provided on request."
    }
    constructor(field: String) : super(String.format(MESSAGE, field))
    constructor(field: String, cause: Throwable?) : super(String.format(MESSAGE, field), cause)
}
