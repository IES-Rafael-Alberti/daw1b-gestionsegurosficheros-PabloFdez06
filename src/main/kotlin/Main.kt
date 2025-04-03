import app.CargadorInicial
import data.IRepoSeguros
import data.IRepoUsuarios
import org.example.UI.Consola
import org.example.Utils.Seguridad
import org.example.service.GestorSeguros
import org.example.service.GestorUsuarios

fun main() {
    // Rutas de los archivos de texto para almacenamiento
    val usuariosFilePath = "path/to/usuarios.txt"  // Especifica la ruta del fichero de usuarios
    val segurosFilePath = "path/to/seguros.txt"    // Especifica la ruta del fichero de seguros

    // Instanciamos los componentes del sistema
    val consola = Consola()
    val entradaSalida = EntradaSalida()
    val seguridad = Seguridad()

    // Limpiamos la pantalla
    consola.limpiarPantalla()

    // Preguntamos al usuario si desea iniciar en modo simulación o almacenamiento
    val modo = consola.leerEntrada("¿Desea iniciar en modo SIMULACIÓN o ALMACENAMIENTO? (S/A)").uppercase()

    // Declaramos los repositorios de usuarios y seguros
    val repoUsuarios: IRepoUsuarios = if (modo == "A") ReposUsuariosFich(usuariosFilePath) else ReposUsuariosMem()
    val repoSeguros: IRepoSeguros = if (modo == "A") ReposSegurosFich(segurosFilePath) else ReposSegurosMem()

    // Cargador de información inicial
    val cargador = CargadorInicial(repoUsuarios, repoSeguros)
    cargador.cargar()

    // Servicios de lógica de negocio
    val gestorUsuarios = GestorUsuarios(repoUsuarios, seguridad)
    val gestorSeguros = GestorSeguros(repoSeguros, seguridad)

    // Proceso de autenticación
    val usuarioAutenticado = controlAcceso.autenticarUsuario(gestorUsuarios)
    if (usuarioAutenticado == null) {
        // Crear usuario ADMIN si no hay usuarios
        gestorUsuarios.agregarUsuario("ADMIN", "admin@example.com")
    } else {
        // Iniciar el menú correspondiente según el perfil del usuario autenticado
        when (usuarioAutenticado.perfil) {
            "ADMIN" -> iniciarMenu(gestorUsuarios, gestorSeguros, 1) // Admin
            "GESTION" -> iniciarMenu(gestorUsuarios, gestorSeguros, 2) // Gestión
            "CONSULTA" -> iniciarMenu(gestorUsuarios, gestorSeguros, 3) // Consulta
        }
    }
}

// Función para iniciar el menú según el perfil del usuario
fun iniciarMenu(gestorUsuarios: GestorUsuarios, gestorSeguros: GestorSeguros, perfil: Int) {
    // Implementar la lógica del menú basado en el perfil aquí
    // Puedes usar un bucle para mostrar opciones y manejar la selección del usuario
}