package br.com.rfasioli.bootstrap.architecture

import br.com.rfasioli.bootstrap.api.IntegrationTest
import com.tngtech.archunit.base.DescribedPredicate.not
import com.tngtech.archunit.core.domain.JavaClass.Predicates.equivalentTo
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.CompositeArchRule
import com.tngtech.archunit.lang.conditions.ArchPredicates.are
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.GeneralCodingRules.ACCESS_STANDARD_STREAMS
import com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS
import com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS
import com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION
import com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING
import com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition

@AnalyzeClasses(
    packages = ["br.com.rfasioli.bootstrap.api"],
    importOptions = [ImportOption.DoNotIncludeTests::class]
)
class CodingRulesTest {

    @ArchTest
    private val noAccessToStandardStreams = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS

    @ArchTest
    public fun noAccessToStandardStreamsAsMethod(classes: JavaClasses) {
        noClasses().should(ACCESS_STANDARD_STREAMS).check(classes)
    }

    @ArchTest
    private val noGenericExceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS

    @ArchTest
    private val noJavaUtilLogging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING

    @ArchTest
    private val noJodatime = NO_CLASSES_SHOULD_USE_JODATIME

    @ArchTest
    public fun noFieldInjection(classes: JavaClasses) {
        NO_CLASSES_SHOULD_USE_FIELD_INJECTION
            .allowEmptyShould(true)
            .check(classes.that(are(not(equivalentTo(IntegrationTest::class.java)))))
    }

    @ArchTest
    private val noClassesShouldAccessStandardStreamsOrThrowGenericExceptions: ArchRule =
        CompositeArchRule.of(NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS)
            .and(NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS)

    @ArchTest
    private val noCyclicRedundances: ArchRule =
        SlicesRuleDefinition.slices()
            .matching("br.com.rfasioli.bootstrap.api.(*)..")
            .should().beFreeOfCycles()
}
