package br.com.rfasioli.bootstrap.api.adapter.input.web.mapper

import br.com.rfasioli.bootstrap.api.UnitTest
import br.com.rfasioli.bootstrap.api.domain.model.Gender
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class GenderMapperKtTest : UnitTest() {

    @ParameterizedTest
    @CsvSource(*["MALE,MALE", "FEMALE,FEMALE", "FEME,OTHER", ",UNINFORMED"])
    fun `Should convert from string to valid Gender`(
        source: String?,
        expected: Gender
    ) {
        val result = genderFromString(source)
        assertEquals(expected, result)
    }
}
