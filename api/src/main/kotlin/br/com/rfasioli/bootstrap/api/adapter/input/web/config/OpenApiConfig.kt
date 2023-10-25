package br.com.rfasioli.bootstrap.api.adapter.input.web.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Spring Bootstrap App",
        version = "1.0",
        description = "This API provides resources for SDA Bootstrap Application."
    )
)
class OpenApiConfig
