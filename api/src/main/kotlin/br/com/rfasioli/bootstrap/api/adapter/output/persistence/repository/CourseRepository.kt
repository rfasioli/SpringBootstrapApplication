package br.com.rfasioli.bootstrap.api.adapter.output.persistence.repository

import br.com.rfasioli.bootstrap.api.adapter.output.persistence.entity.CourseEntity
import br.com.rfasioli.bootstrap.api.domain.model.Stage
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.UUID

@Repository
interface CourseRepository : R2dbcRepository<CourseEntity, UUID> {
    fun findByStageIn(stages: Collection<Stage>): Flux<CourseEntity>
}
