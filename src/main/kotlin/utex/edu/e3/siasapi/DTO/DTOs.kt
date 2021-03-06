package utex.edu.e3.siasapi.DTO

import utex.edu.e3.siasapi.Entity.Rol
import utex.edu.e3.siasapi.Entity.Usuario
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.FetchType
import javax.persistence.OneToMany

data class AuthenticationDTO(
    var usuario: String = "",
    var contra: String
)

data class UsuarioDTO(
    var id: Long? = null,
    var correo:String = "",
    var estado: String = "",
    var ultimoLogin: Date? = null,
    var rol: String = ""
)

data class RolDTO(
    var id:Long? = null,
    var rol: String = "",
    var estado: String = "",
)

data class EstudianteDTO(
        var id:Long? = null,
        var nombre: String = "",
        var primerApellido: String = "",
        var segundoApellido: String = "",
        var matricula: String = "",
        var usuario: String = "",
        var carrera: String = "",
        var divisionEst: String = ""
)

data class DivisionDTO(
        var id:Long?= null,
        var nombre: String = "",
        var siglas: String = ""
)