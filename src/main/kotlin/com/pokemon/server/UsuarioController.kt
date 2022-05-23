package com.pokemon.server

import org.springframework.web.bind.annotation.*

@RestController
class UsuarioController(private val usuarioRepository: UsuarioRepository) {

    // Podemos hacer la request desde el navegador.
    @GetMapping("crearUsuario/{nombre}/{pass}")
    @Synchronized
    fun requestCrearUsuario(@PathVariable nombre: String, @PathVariable pass: String): Any {
        val userOptinal = usuarioRepository.findById(nombre)

        return if (userOptinal.isPresent) {
            val user = userOptinal.get()
            if (user.pass == pass) {
                user
            } else {
                "Contraseña incorrecta"
            }
        } else {
            val user = Usuario(nombre, pass)
            usuarioRepository.save(user)
            user
        }
    }

    /*
    curl --request POST  --header "Content-type:application/json" --data "{\"nombre\":\"u2\", \"pass\":\"p2\"}" localhost:8084/crearUsuario {"nombre":"u2","pass":"p2","token":"u2p2"}
     */
    @PostMapping("crearUsuario")
    @Synchronized
    fun requestCrearUsuarioJson(@RequestBody usuario: Usuario): Any {
        val userOptinal = usuarioRepository.findById(usuario.nombre)

        return if (userOptinal.isPresent) {
            val user = userOptinal.get()
            if (user.pass == usuario.pass) {
                user
            } else {
                "Contraseña incorrecta"
            }
        } else {
            usuarioRepository.save(usuario)
            usuario
        }
    }


    @PostMapping("pokemonFavorito/{token}")
    fun guardarPokemonFavorito(@PathVariable token: String): Any {
        println(token)
        usuarioRepository.findAll().forEach { user ->
            if (user.token == token) {
                listaPokemon.listaPokemon.forEach {
                    if (user.pokemonFavoritoId?.toLong() == it.id) {
                        return it
                    }
                }
                return "el usuario no tiene pokemon favorito"
            } else
                return "Token No  valido"
        }
        return "Token no encontrado"
    }


    @PostMapping("pokemonCapturado/{token}/{pokemonId}")
    fun pokemonCapturado(@PathVariable token: String, @PathVariable pokemonId: Int): String {

        usuarioRepository.findAll().forEach { user ->

            if (user.token == token) {
                user.pokemonCapturado.add(pokemonId)
                usuarioRepository.save(user)
                return "El usuario ${user.nombre} tiene ${user.pokemonCapturado} capturados"

            }
        }

        return "token no encontrado"

    }


    @GetMapping("mostrarPokemonCapturados/{token}")
    fun requestMostrarPokemonCapturados(@PathVariable token: String): Any {

        var listacapturados = mutableListOf<String>()

        usuarioRepository.findAll().forEach { user ->

            if (user.token == token) {

                user.pokemonCapturado.forEach { idPokemon ->

                    println(idPokemon)
                    listaPokemon.listaPokemon.forEach {pokemon ->
                        if (idPokemon.toLong() == pokemon.id) {
                            println(pokemon.name)
                            listacapturados.add(pokemon.name)
                        }

                    }

                }

            }

        }

        return if (listacapturados.isEmpty())
            "El usuario no ha capturado"
        else
            listacapturados


    }
}