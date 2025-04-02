package org.example.Utils

interface IUtilSeguridad {
    fun encriptarClave(clave: String, nivelSeguridad: Int = 12): String
    fun verificarClave(claveIngresada: String, hashAlmacenado: String): Boolean
}