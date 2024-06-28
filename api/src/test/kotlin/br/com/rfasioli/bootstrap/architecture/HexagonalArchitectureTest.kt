package br.com.rfasioli.bootstrap.architecture

import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures.layeredArchitecture

@AnalyzeClasses(
    packages = ["br.com.rfasioli.bootstrap.api"],
    importOptions = [ImportOption.DoNotIncludeTests::class],
)
class HexagonalArchitectureTest {
    @ArchTest
    val hexagonalArchitectureLayersIsRespected: ArchRule =
        layeredArchitecture().consideringAllDependencies()
            .layer("DomainServices").definedBy("..domain.usecase..")
            .layer("PortIn").definedBy("..port.input..")
            .layer("PortOut").definedBy("..port.output..")
            .layer("AdapterIn").definedBy("..adapter.input..")
            .layer("AdapterOut").definedBy("..adapter.output..")
            .whereLayer("AdapterIn").mayNotBeAccessedByAnyLayer()
            .whereLayer("AdapterOut").mayNotBeAccessedByAnyLayer()
            .whereLayer("DomainServices").mayNotBeAccessedByAnyLayer()
            .whereLayer("PortIn").mayOnlyBeAccessedByLayers("DomainServices", "AdapterIn")
            .whereLayer("PortOut").mayOnlyBeAccessedByLayers("DomainServices", "AdapterOut")
            .allowEmptyShould(true)
}
