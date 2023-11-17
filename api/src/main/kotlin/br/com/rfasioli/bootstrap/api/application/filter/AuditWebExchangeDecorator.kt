package br.com.rfasioli.bootstrap.api.application.filter

import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.ServerWebExchangeDecorator

class AuditWebExchangeDecorator(serverWebExchange: ServerWebExchange)
    : ServerWebExchangeDecorator(serverWebExchange)
{
    override fun getRequest(): ServerHttpRequest {
        return super.getRequest()
    }

    override fun getResponse(): ServerHttpResponse {
        return super.getResponse()
    }

}
