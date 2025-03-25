package model

abstract class Seguro(
    val numPoliza: Int,
    val dniTitular: String,
    var importe: Double
) : IExportable {

    companion object {
        var numPolizasAuto = 400000
        var numPolizasHogar = 100000
        var numPolizasVida = 800000
    }

    abstract fun calcularImporteAnioSiguiente(interes: Double): Double

    abstract fun tipoSeguro(): String

    override fun serializar(separador: String): String {
        return "$numPoliza$separador$dniTitular$separador$importe"
    }

    override fun toString(): String {
        return "Seguro(numPoliza = $numPoliza, dniTitular = $dniTitular, importe = $importe)"
    }

    override fun hashCode(): Int {
        return numPoliza
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val seguro = other as Seguro
        return numPoliza == seguro.numPoliza
    }
}
