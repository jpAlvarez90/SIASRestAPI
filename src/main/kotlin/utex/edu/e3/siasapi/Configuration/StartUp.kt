package utex.edu.e3.siasapi.Configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import utex.edu.e3.siasapi.Entity.Rol
import utex.edu.e3.siasapi.Entity.Usuario
import utex.edu.e3.siasapi.Repository.RolRepository
import utex.edu.e3.siasapi.Service.RolService
import utex.edu.e3.siasapi.Service.UsuarioService
import java.util.*

@Component
class StartUp(
    @Autowired val rolService: RolService,
    @Autowired val usuarioService: UsuarioService,
    @Autowired val passwordEncoder: BCryptPasswordEncoder
): CommandLineRunner {
    override fun run(vararg args: String?) {
        val roleDocente = "ROLE_DOCENTE"
        val roleEstudiante = "ROLE_ESTUDIANTE"
        val roleCoordinador = "ROLE_COORDINADOR"
        val roleAdmin = "ROLE_ADMIN"

        val correoAdmin = "admin@utez.edu.mx"
        val contraAdmin = "admin"

        if (!rolService.existeRolPorNombre(roleDocente)) {
            val rol:Rol = Rol(roleDocente,"Activo")
            rolService.guardarRol(rol)
        }

        if (!rolService.existeRolPorNombre(roleEstudiante)) {
            val rol:Rol = Rol(roleEstudiante,"Activo")
            rolService.guardarRol(rol)
        }

        if (!rolService.existeRolPorNombre(roleCoordinador)) {
            val rol:Rol = Rol(roleCoordinador,"Activo")
            rolService.guardarRol(rol)
        }

        if (!rolService.existeRolPorNombre(roleAdmin)) {
            val rol:Rol = Rol(roleAdmin,"Activo")
            rolService.guardarRol(rol)
        }

        if (!usuarioService.existeUsuarioPorCorreo(correoAdmin)) {
            var usuario:Usuario = Usuario(correoAdmin,passwordEncoder.encode(contraAdmin),"Activo")
            usuario.rol = rolService.encontrarPorRol(roleAdmin)
            usuarioService.guardarUsuario(usuario)
        }
    }
}