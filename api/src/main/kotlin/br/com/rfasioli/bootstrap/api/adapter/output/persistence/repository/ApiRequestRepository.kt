package br.com.rfasioli.bootstrap.api.adapter.output.persistence.repository

import br.com.rfasioli.bootstrap.api.adapter.output.persistence.entity.CourseEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ApiRequestRepository : R2dbcRepository<CourseEntity, UUID>
