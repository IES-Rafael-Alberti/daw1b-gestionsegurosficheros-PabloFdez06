package model

class Usuario(val nombre: String, private var clave: String, val perfil: Perfil) : IExportable {


    init {
        require(nombre.isNotBlank()) { "El nombre no puede estar vacío" }
        require(clave.isNotBlank()) { "La clave no puede estar vacía" }
        require(Perfil.values().contains(perfil)) { "Perfil inválido" }
    }

    companion object {
        fun crearUsuario(datos: List<String>): Usuario {
            val nombre = datos[0]
            val clave = datos[1]
            val perfil = Perfil.getPerfil(datos[2])

            return Usuario(nombre, clave, perfil)
        }
    }

    fun cambiarClave(nuevaClave: String): Boolean {
        if (nuevaClave.isNotEmpty()) {
            this.clave = nuevaClave
            return true
        }
        return false
    }

    override fun serializar(separador: String): String {
        return "$nombre$separador$clave$separador$perfil"
    }
}
