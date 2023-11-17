package br.com.rfasioli.bootstrap.api.application.filter

import br.com.rfasioli.bootstrap.api.adapter.output.persistence.repository.ApiRequestRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class AuditWebFilter(
    apiRequestRepository: ApiRequestRepository
) : WebFilter {
    override fun filter(
        serverWebExchange: ServerWebExchange,
        webFilterChain: WebFilterChain,
    ): Mono<Void> {
        return webFilterChain.filter(
            AuditWebExchangeDecorator(serverWebExchange)
        )
    }
}
