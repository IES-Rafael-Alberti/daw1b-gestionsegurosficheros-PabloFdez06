package model

import java.time.LocalDate


class SeguroHogar: Seguro {
    private var metrosCuadrados: Int
    private var valorContenido: Int
    private var direccion: String
    private var anioConstruccion: LocalDate


    companion object{
        const val PORCENTAJE_INCREMENTO_ANIOS = 0.02
        const val CICLO_ANIOS_INCREMENTO = 5
        var numPolizasHogar: Int = 100000



        fun crearSeguro(datos: List<String>) : SeguroHogar? {
            return try {
                if (datos.size < 7) return null

                val numPoliza = datos[0].toInt()
                val dniTitular = datos[1]
                val importe = datos[2].toDouble()
                val metrosCuadrados = datos[3].toInt()
                val valorContenido = datos[4].toInt()
                val direccion = datos[5]
                val anioConstruccion = LocalDate.parse(datos[6])


                SeguroHogar(numPoliza, dniTitular, importe, metrosCuadrados, valorContenido, direccion, anioConstruccion)
            } catch (e: Exception) {
                null
            }
        }
    }


    constructor(dniTitular: String, importe: Double, metrosCuadrados: Int, valorContenido: Int, direccion: String, anioConstruccion: LocalDate) :
            super(numPoliza = numPolizasHogar++, dniTitular, importe) {
                this.metrosCuadrados = metrosCuadrados
                this.valorContenido = valorContenido
                this.direccion = direccion
                this.anioConstruccion = anioConstruccion
            }


    private constructor(numPoliza: Int, dniTitular: String, importe: Double, metrosCuadrados: Int, valorContenido: Int, direccion: String, anioConstruccion: LocalDate) :
            super(numPoliza, dniTitular, importe) {
                this.metrosCuadrados = metrosCuadrados
                this.valorContenido = valorContenido
                this.direccion = direccion
                this.anioConstruccion = anioConstruccion
    }

    override fun calcularImporteAnioSiguiente(interes: Double): Double {
        val aniosAntiguedad = LocalDate.now().year - anioConstruccion.year
        val incrementoAdicional = (aniosAntiguedad / CICLO_ANIOS_INCREMENTO) * PORCENTAJE_INCREMENTO_ANIOS
        return importe * (1 + interes + incrementoAdicional)
    }


    override fun serializar(separador: String): String {
        return super.serializar(separador) + "$metrosCuadrados$separador$valorContenido$separador$direccion$separador$anioConstruccion$"
    }

    override fun toString(): String {
        return "Seguro Hogar(numPoliza= ${super.numPoliza}, dniTitular= ${super.dniTitular}, importe= ${"%.2f".format(super.importe)}, metrosCuadrados= $metrosCuadrados, valorContenido= $valorContenido, direccion= $direccion, anioConstruccion= $anioConstruccion)"
    }

}