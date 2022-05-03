package com.pokemon.server

import Pokemon
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicInteger

@RestController
class PokemonController {

    var numRequestRecibidas = AtomicInteger(0)

    @GetMapping("hola")
    fun requestHola() : String {
        val numRequestRecibidasLocal = numRequestRecibidas.getAndIncrement()
        println("Me han dicho Hola $numRequestRecibidasLocal veces")
        Thread.sleep(4000)
        println("Voy a decir Buenas por vez $numRequestRecibidasLocal")
        return "Buenas! $numRequestRecibidasLocal"
    }

    @GetMapping("pokemon")
    fun requestPokemon() : ListaPokemon {
        return listaPokemon
    }

    @GetMapping("pokemonPorNombre/{nombre}")
    fun requestPokemonNombre(@PathVariable nombre: String) : ListaPokemon {
        return listaPokemon.buscarPokemonPorNombre(nombre)
    }

    @GetMapping("pokemonPorTipo/{tipo}")
    fun requestPokemonTipo(@PathVariable tipo: String) : ListaPokemon {
        return listaPokemon.buscarPokemonPorTipo(tipo)
    }

    @GetMapping("pokemonMasPequeno")
    fun requestPokemonPeque() : Pokemon {
        return listaPokemon.buscarPokemonPeque()
    }

    @GetMapping("pokemonMasGrande")
    fun requestPokemonGrande() : Pokemon {
        return listaPokemon.buscarPokemonGrande()
    }

    @GetMapping("pokemonMasGordo")
    fun requestPokemonGordo() : Pokemon {
        return listaPokemon.buscarPokemonGordo()
    }

    @GetMapping("pokemonMasGordoQue/{peso}")
    fun requestPokemonGordo(@PathVariable peso : Int) : ListaPokemon {
        return listaPokemon.buscarPokemonMasGordoQue(peso)
    }

    @GetMapping("pokemonMasGordoPorTipo/{tipo}")
    fun requestPokemonGordoPorTipo(@PathVariable tipo : String) : Pokemon {
        return listaPokemon.buscarPokemonMasGordoPorTipo(tipo)
    }

    @GetMapping("pokemonMasGordoPorTipo/{tipo}/{peso}")
    fun requestPokemonGordoPorTipoYPeso(@PathVariable tipo : String, @PathVariable peso : Int) : ListaPokemon {
        return listaPokemon.buscarPokemonMasGordoPorTipoYPeso(tipo, peso)
    }

    @GetMapping("pokemonPorAtaque/{ataque}")
    fun requestPokemonPorAtaque(@PathVariable ataque : String) : ListaPokemon {
        return listaPokemon.buscarPokemonPorAtaque(ataque)
    }
}