package com.pokemon.server

import Pokemon
import java.io.File

class ListaPokemon(var listaPokemon : MutableList<Pokemon> = mutableListOf()) {


    companion object{
        const val filePath = "pokemons.json"

        fun fileExist() : Boolean {
            return File(filePath).exists()
        }

        fun cargarListaPokemonDeFichero() : ListaPokemon {
            val lista = gson.fromJson(File(filePath).readText(), ListaPokemon::class.java)
            return lista
        }

    }

    fun agregar(pokemon: Pokemon) {
        listaPokemon.add(pokemon)
    }

    fun imprimirPokemons(){
        if (listaPokemon.isEmpty()) {
            println("No se ha encontrado a ese Pokémon")
        } else {
            listaPokemon.forEach {
                println(it.decirNombreYTipo())
            }
        }
    }

    fun buscarPokemonPorNombre(nombreBuscado : String) : ListaPokemon {
        // TODO muéstrame al Pokemon que las letras buscadas incluidas en su nombre. Si no hay, dime que no hay
        val listaFiltrada = listaPokemon.filter {
            it.name.contains(nombreBuscado)
        }

        return ListaPokemon(listaFiltrada.toMutableList())
    }

    fun buscarPokemonPorTipo(tipoBuscado : String) : ListaPokemon {
        // TODO muéstrame todos los pokemon de ese tipo. Si no hay, dime que no hay

        val listaFiltrada = listaPokemon.filter { pokemon ->
            var encontrado = false
            pokemon.types.forEach {  tipo ->
                if (tipo.type.name == tipoBuscado)
                    encontrado = true
            }
            encontrado
        }

        return ListaPokemon(listaFiltrada.toMutableList())
    }

    fun buscarPokemonPeque() : Pokemon {
        val listaOrdenada = listaPokemon.sortedBy {
            it.height
        }

        return listaOrdenada.first()
    }

    fun buscarPokemonGrande() : Pokemon {
        val listaOrdenada = listaPokemon.sortedBy {
            it.height
        }

        return listaOrdenada.last()
    }

    fun buscarPokemonGordo() : Pokemon {
        val listaOrdenada = listaPokemon.sortedBy {
            it.weight
        }

        return listaOrdenada.last()
    }

    fun buscarPokemonMasGordoQue(peso : Int) : ListaPokemon {
        val listaFiltrada = listaPokemon.filter {
            it.weight > peso
        }

        return ListaPokemon(listaFiltrada.toMutableList())
    }

    fun guardarEnFichero(){
        val file = File(filePath)
        file.writeText(gson.toJson(this))
    }

    fun buscarPokemonMasGordoPorTipo(tipoBuscado: String): Pokemon {
        val listaOrdenada = listaPokemon.sortedBy {
            it.weight
        }
        val listaFiltrada = listaOrdenada.filter { pokemon ->
            var encontrado = false
            pokemon.types.forEach {  tipo ->
                if (tipo.type.name == tipoBuscado)
                    encontrado = true
            }
            encontrado
        }
        return listaFiltrada.last()
    }

    fun buscarPokemonMasGordoPorTipoYPeso(tipoBuscado: String, pesoMinimo : Int): ListaPokemon {
        val listaOrdenada = listaPokemon.sortedBy {
            it.weight
        }
        val listaFiltrada = listaOrdenada.filter { pokemon ->
            var encontrado = false
            pokemon.types.forEach {  tipo ->
                if (tipo.type.name == tipoBuscado)
                    encontrado = true
            }
            encontrado && pokemon.weight > pesoMinimo
        }

        return ListaPokemon(listaFiltrada.sortedBy { it.id }.toMutableList())
    }

    fun buscarPokemonPorAtaque(ataque: String): ListaPokemon {
        val listaFiltrada = listaPokemon.filter { pokemon ->
            var encontrado = false
            pokemon.moves.forEach {  move ->
                if (move.move.name == ataque)
                    encontrado = true
            }
            encontrado
        }

        return ListaPokemon(listaFiltrada.toMutableList())
    }
}