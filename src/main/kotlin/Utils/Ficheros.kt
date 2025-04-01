package org.example.Utils

import model.IExportable
import java.io.File
import java.io.IOException

class Ficheros : IUtilFicheros {
    override fun leerArchivo(ruta: String): List<String> {
        return try {
            File(ruta).readLines()
        } catch (e: IOException) {
            emptyList()
        }
    }

    override fun agregarLinea(ruta: String, linea: String): Boolean {
        return try {
            File(ruta).appendText("$linea\n")
            true
        } catch (e: IOException) {
            false
        }
    }

    override fun <T: IExportable> escribirArchivo(ruta: String, elementos: List<T>): Boolean {
        return try {
            File(ruta).writeText(elementos.joinToString("\n") { it.serializar() })
            true
        } catch (e: IOException) {
            false
        }
    }

    override fun existeFichero(ruta: String): Boolean {
        return File(ruta).exists()
    }

    override fun existeDirectorio(ruta: String): Boolean {
        return File(ruta).isDirectory
    }
}