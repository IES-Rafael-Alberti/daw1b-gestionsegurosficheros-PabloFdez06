package org.example.service

import model.Usuario
import data.IRepoUsuarios
import model.Perfil
import org.example.Utils.IUtilSeguridad
import service.IServUsuarios

class GestorUsuarios(
    private val repoUsuarios: IRepoUsuarios,
    private val utilSeguridad: IUtilSeguridad
) : IServUsuarios {

    override fun iniciarSesion(nombre: String, clave: String): Perfil? {
        val usuario = repoUsuarios.buscar(nombre) ?: return null
        return if (utilSeguridad.verificarClave(clave, usuario.clave)) usuario.perfil else null
    }

    override fun agregarUsuario(nombre: String, clave: String, perfil: Perfil): Boolean {
        if (repoUsuarios.buscar(nombre) != null) return false
        val claveEncriptada = utilSeguridad.encriptarClave(clave)
        val nuevoUsuario = Usuario(nombre, claveEncriptada, perfil)
        return repoUsuarios.agregar(nuevoUsuario)
    }

    override fun eliminarUsuario(nombre: String): Boolean {
        return repoUsuarios.eliminar(nombre)
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        val claveEncriptada = utilSeguridad.encriptarClave(nuevaClave)
        return repoUsuarios.cambiarClave(usuario, claveEncriptada)
    }

    override fun buscarUsuario(nombre: String): Usuario? {
        return repoUsuarios.buscar(nombre)
    }

    override fun consultarTodos(): List<Usuario> {
        return repoUsuarios.obtenerTodos()
    }

    override fun consultarPorPerfil(perfil: Perfil): List<Usuario> {
        return repoUsuarios.obtener(perfil)
    }
}