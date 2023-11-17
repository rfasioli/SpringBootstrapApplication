package br.com.rfasioli.bootstrap.api.application.filter

import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.HttpMethod
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpRequestDecorator
import reactor.core.publisher.Flux
import java.util.Optional

class AuditRequestDecorator internal constructor(
    delegate: ServerHttpRequest
) : ServerHttpRequestDecorator(delegate) {

    override fun getBody(): Flux<DataBuffer> {
        return body!!
    }

    init {
        val method = Optional.ofNullable(delegate.method).orElse(HttpMethod.GET).name()
        val path = delegate.uri.path
        val query = delegate.uri.query
        val headers = delegate.headers.toString()

        super.getBody().collectList().subscribe{ dataList ->

        }
    }
}
