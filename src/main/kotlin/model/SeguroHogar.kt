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

        fun obtenerNumPolizaHogar(): Int {
            return numPolizasHogar++
        }

        fun crearSeguro(datos: List<String>) : SeguroHogar?{

            try { // controlar fuera, donde pedimos el seguro.
                if (datos.size == 7) {
                    datos[0].toInt()
                    datos[1].isNotEmpty()
                    datos[2].toDouble()
                    datos[3].toInt()
                    datos[4].toInt()
                    datos[5].isNotEmpty()
                    LocalDate.parse(datos[6])
                }
            } catch (e: IllegalArgumentException) {
                return null
            }
            return // TODO
        }
    }


    constructor(dniTitular: String, importe: Double, metrosCuadrados: Int, valorContenido: Int, direccion: String, anioConstruccion: LocalDate) :
            super(numPoliza = obtenerNumPolizaHogar(), dniTitular, importe) {
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
        TODO("Not yet implemented")
    }

    override fun tipoSeguro(): String {
        return this::class.simpleName ?: "Desconocido"
    }

    override fun serializar(separador: String): String {
        return super.serializar(separador) + "$metrosCuadrados$separador$valorContenido$separador$direccion$separador$anioConstruccion$"
    }
}