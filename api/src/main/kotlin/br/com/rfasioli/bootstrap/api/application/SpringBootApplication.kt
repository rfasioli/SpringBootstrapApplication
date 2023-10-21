package br.com.rfasioli.bootstrap.api.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@ComponentScan(basePackages = [ "br.com.rfasioli.bootstrap.api" ])
@EnableR2dbcRepositories(basePackages = [ "br.com.rfasioli.bootstrap.api.adapter.output.persistence" ])
class SpringBootstrapApplication

fun main(args: Array<String>) {
    runApplication<SpringBootstrapApplication>(*args)
}
