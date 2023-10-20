package br.com.rfasioli.bootstrap.architecture

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures.onionArchitecture


@AnalyzeClasses(packages = [ "br.com.rfasioli.bootstrap.api" ])
class OnionArchitectureTest {

    @ArchTest
    val `onion architecture is respected`: ArchRule = onionArchitecture()
        .domainModels("..domain.model..")
        .domainServices("..domain.usecase..")
        .applicationServices("..application..")
        .adapter("consumer", "..adapter.input.messaging..")
        .adapter("rest", "..adapter.input.rest..")
        .adapter("producer", "..adapter.input.messaging..")
        .adapter("persistence", "..adapter.output.persistence..")
        .adapter("webclient", "..adapter.output.webclient..")
        .allowEmptyShould(true)

}
