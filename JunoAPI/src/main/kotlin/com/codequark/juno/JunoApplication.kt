package com.codequark.juno

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JunoApplication

fun main(args: Array<String>) {
	runApplication<JunoApplication>(*args)
}