package br.com.rfasioli.bootstrap.api.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = [ "br.com.rfasioli.bootstrap.api" ])
class SpringBootstrapApplication

fun main(args: Array<String>) {
    runApplication<SpringBootstrapApplication>(*args)
}
