package br.com.rfasioli.bootstrap.api

import br.com.rfasioli.bootstrap.api.application.SpringBootstrapApplication
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [ SpringBootstrapApplication::class ],
)
@AutoConfigureWebTestClient()
@ExtendWith(SpringExtension::class)
@AutoConfigureWireMock(stubs = ["classpath:/stubs"], port = 0)
@Tag("Integration")
abstract class IntegrationTest {
    @Autowired
    protected lateinit var webTestClient: WebTestClient

    @Autowired
    protected lateinit var objectMapper: ObjectMapper
}
