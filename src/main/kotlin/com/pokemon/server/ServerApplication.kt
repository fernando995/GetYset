package com.pokemon.server

import com.google.gson.Gson
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServerApplication

var gson = Gson()

fun main(args: Array<String>) {
	runApplication<ServerApplication>(*args)
}
