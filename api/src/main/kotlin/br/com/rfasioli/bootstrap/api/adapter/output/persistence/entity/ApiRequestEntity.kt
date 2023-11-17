package br.com.rfasioli.bootstrap.api.adapter.output.persistence.entity

import org.springframework.data.annotation.Id
import java.util.UUID

data class ApiRequestEntity(
    @Id
    val id: UUID?,

    val method: String,

    val path: String,

    val query: String,

    val headers: String,

    val body: String,

)
