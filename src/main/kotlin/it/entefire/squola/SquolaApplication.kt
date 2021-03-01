package it.entefire.squola

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SquolaApplication

fun main(args: Array<String>) {
	runApplication<SquolaApplication>(*args)
}
