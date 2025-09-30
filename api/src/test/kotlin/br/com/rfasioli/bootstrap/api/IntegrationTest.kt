package br.com.rfasioli.bootstrap.api

import PostgresTestcontainerConfiguration
import br.com.rfasioli.bootstrap.api.application.SpringBootstrapApplication
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.context.annotation.Import
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [ SpringBootstrapApplication::class ],
)
@Import(PostgresTestcontainerConfiguration::class)
@EnableR2dbcRepositories("br.com.rfasioli.bootstrap.api.adapter.output.persistence")
@AutoConfigureWebTestClient
@AutoConfigureWebFlux
@AutoConfigureWireMock(stubs = ["classpath:/stubs"], port = 0)
@Tag("Integration")
abstract class IntegrationTest {
    @Autowired
    protected lateinit var webTestClient: WebTestClient

    @Autowired
    protected lateinit var objectMapper: ObjectMapper
}
