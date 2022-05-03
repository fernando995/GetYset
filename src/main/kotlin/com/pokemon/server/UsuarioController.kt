package com.pokemon.server

import org.springframework.web.bind.annotation.*

@RestController
class UsuarioController(private val usuarioRepository: UsuarioRepository) {

    @GetMapping("crearUsuario/{nombre}/{pass}")
    @Synchronized
    fun requestCrearUsuario(@PathVariable nombre: String, @PathVariable pass: String) : Any {
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

    @PostMapping("crearUsuario")
    @Synchronized
    fun requestCrearUsuarioJson(@RequestBody usuario: Usuario) : Any {
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
}