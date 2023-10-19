package br.com.rfasioli.bootstrap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootstrapApplication

fun main(args: Array<String>) {
	runApplication<SpringBootstrapApplication>(*args)
}
