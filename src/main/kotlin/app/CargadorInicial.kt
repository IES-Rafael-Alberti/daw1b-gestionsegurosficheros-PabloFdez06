package app

import com.sun.media.sound.InvalidFormatException
import data.ICargarSegurosIniciales
import data.ICargarUsuariosIniciales
import org.example.UI.IEntradaSalida
import java.io.IOException

/**
 * Clase encargada de cargar los datos iniciales de usuarios y seguros desde ficheros,
 * necesarios para el funcionamiento del sistema en modo persistente.
 *
 * @param ui Interfaz de entrada/salida para mostrar mensajes de error.
 * @param repoUsuarios Repositorio que permite cargar usuarios desde un fichero.
 * @param repoSeguros Repositorio que permite cargar seguros desde un fichero.
 */
class CargadorInicial(
    private val ui: IEntradaSalida,
    private val repoSeguros: ICargarSegurosIniciales,
    private val repoUsuarios: ICargarUsuariosIniciales
) {

    /**
     * Carga los usuarios desde el archivo configurado en el repositorio.
     * Muestra errores si ocurre un problema en la lectura o conversión de datos.
     */
    private fun cargarUsuarios() {
        try {
            repoUsuarios.cargarUsuarios()
            ui.mostrar("Usuarios cargados correctamente.")
        }catch (e: InvalidFormatException) {
            ui.mostrarError("ERROR. Formato de datos incorrecto. ${e.message}")
        }catch (e: IOException){
            ui.mostrarError("ERROR. No se pudo leer el fichero. ${e.message}")
        }catch(e: Exception){
            ui.mostrarError("ERROR. ${e.message}")
        }
    }

    /**
     * Carga los seguros desde el archivo configurado en el repositorio.
     * Utiliza el mapa de funciones de creación definido en la configuración de la aplicación
     * (ConfiguracionesApp.mapaCrearSeguros).
     * Muestra errores si ocurre un problema en la lectura o conversión de datos.
     */
    fun cargarSeguros() {
        try {
            repoSeguros.cargarSeguros(ConfiguracionesApp.mapaCrearSeguros)
            ui.mostrar("Seguros cargados correctamente.")
        }catch (e: InvalidFormatException) {
            ui.mostrarError("ERROR. Formato de datos incorrecto. ${e.message}")
        }catch (e: IOException){
            ui.mostrarError("ERROR. No se pudo leer el fichero. ${e.message}")
        }catch(e: Exception){
            ui.mostrarError("ERROR. ${e.message}")
        }
    }

}