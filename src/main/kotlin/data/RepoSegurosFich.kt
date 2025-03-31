package org.example.data

import data.ICargarSegurosIniciales
import data.ICargarUsuariosIniciales
import data.RepoUsuariosMem
import model.Seguro
import model.SeguroHogar
import model.Usuario
import org.example.model.SeguroAuto
import org.example.model.SeguroVida

class RepoSegurosFich(
    private val rutaArchivo: String,
    private val fich: IUtilFicheros
): RepoSegurosMem(), ICargarSegurosIniciales {

    override fun agregar(seguro: Seguro): Boolean {
        if (super.agregar(seguro) && fich.agregarLinea(rutaArchivo, seguro.serializar())) {
            return true
        }
        return false
    }

    override fun eliminar(seguro: Seguro): Boolean {
        if (fich.escribirArchivo(rutaArchivo, seguros.filter { it != seguro })) {
            return super.eliminar(seguro)
        }
        return false
    }

    override fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean {
        val lineas = fich.leerSeguros(rutaArchivo)

        if (lineas.isNotEmpty()) {
            seguros.clear()
            for (linea in lineas) {
                val datos = linea.split(";")
                if (datos.size == 3){
                    seguros.add(Seguro().)
                }
            }
            return seguros.isNotEmpty()
        }
        return false
    }

    private fun actualizarContadores(seguros: List<Seguro>) {
        // Actualizar los contadores de polizas del companion object según el tipo de seguro
        val maxHogar = seguros.filter { it.tipoSeguro() == "SeguroHogar" }.maxOfOrNull { it.numPoliza }
        val maxAuto = seguros.filter { it.tipoSeguro() == "SeguroAuto" }.maxOfOrNull { it.numPoliza }
        val maxVida = seguros.filter { it.tipoSeguro() == "SeguroVida" }.maxOfOrNull { it.numPoliza }

        if (maxHogar != null) SeguroHogar.numPolizasHogar = maxHogar
        if (maxAuto != null) SeguroAuto.numPolizaAuto = maxAuto
        if (maxVida != null) SeguroVida.numPolizaVida = maxVida
    }
}