package br.com.rfasioli.bootstrap.api.application.exception

class InconsistentDataException : InvalidDataException {
    companion object {
        private const val MESSAGE = "Inconsistent data on field %s."
    }
    constructor(field: String) : super(String.format(MESSAGE, field))
    constructor(field: String, cause: Throwable?) : super(String.format(MESSAGE, field), cause)
}
