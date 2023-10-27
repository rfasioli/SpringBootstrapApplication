package br.com.rfasioli.bootstrap.api.domain.model

enum class Stage(val age: IntRange) {
    BERCARIO(1..3),
    PRE_MATERNAL(3..4),
    MATERNAL(4..5),
    PRE_ESCOLA(5..6),
    FUNDAMENTAL_I(6..11),
    FUNDAMENTAL_II(10..15),
    MEDIO(14..18),
    SUPERIOR(16..999),
}
