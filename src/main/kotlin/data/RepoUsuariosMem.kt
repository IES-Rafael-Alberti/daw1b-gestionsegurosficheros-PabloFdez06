package data

import model.Perfil
import model.Usuario


class RepoUsuariosMem : IRepoUsuarios {
    private val usuarios = mutableListOf<Usuario>()

    override fun agregar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) != null) {
            return false
        }
        usuarios.add(usuario)
        return true
    }

    override fun buscar(nombreUsuario: String): Usuario? {
        return usuarios.find { it.nombre == nombreUsuario }
    }

    override fun eliminar(usuario: Usuario): Boolean {
        return usuarios.remove(usuario)
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        val usuario = buscar(nombreUsuario)
        return if (usuario != null) eliminar(usuario) else false
    }

    override fun obtenerTodos(): List<Usuario> {
        return usuarios
    }

    override fun obtener(perfil: Perfil): List<Usuario> {
        return usuarios.filter { it.perfil == perfil }
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        return usuario.cambiarClave(nuevaClave)
    }
}
