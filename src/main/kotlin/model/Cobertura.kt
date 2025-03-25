package model

enum class Cobertura {
    TERCEROS, TERCEROS_AMPLIADO, FRANQUICIA_200, FRANQUICIA_300, FRANQUICIA_400, FRANQUICIA_500, TODO_RIESGO;

    companion object {
        fun getCobertura(valor: String): Cobertura {
            return try {
                valueOf(valor.uppercase())
            } catch (e: IllegalArgumentException) {
                TERCEROS
            }
        }
    }
}