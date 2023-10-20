package br.com.rfasioli.bootstrap.architecture

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.CompositeArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.GeneralCodingRules.ACCESS_STANDARD_STREAMS
import com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS
import com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS
import com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION
import com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING
import com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices


@AnalyzeClasses(packages = [ "br.com.rfasioli.bootstrap.api" ])
class CodingRulesTest {

    @ArchTest
    private val no_access_to_standard_streams = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS

    @ArchTest
    private fun no_access_to_standard_streams_as_method(classes: JavaClasses) {
        noClasses().should(ACCESS_STANDARD_STREAMS).check(classes)
    }

    @ArchTest
    private val no_generic_exceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS

    @ArchTest
    private val no_java_util_logging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING

    @ArchTest
    private val no_jodatime = NO_CLASSES_SHOULD_USE_JODATIME

    @ArchTest
    private val no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION
        .allowEmptyShould(true)

    @ArchTest
    private val no_classes_should_access_standard_streams_or_throw_generic_exceptions: ArchRule =
        CompositeArchRule.of(NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS)
            .and(NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS)

    @ArchTest
    private val `no cyclic redundances`: ArchRule =
        SlicesRuleDefinition.slices()
            .matching("br.com.rfasioli.bootstrap.api.(*)..")
            .should().beFreeOfCycles()

}
