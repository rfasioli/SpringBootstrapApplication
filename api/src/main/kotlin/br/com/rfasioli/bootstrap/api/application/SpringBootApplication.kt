package br.com.rfasioli.bootstrap.api.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootstrapApplication

fun main(args: Array<String>) {
    runApplication<SpringBootstrapApplication>(*args)
}
