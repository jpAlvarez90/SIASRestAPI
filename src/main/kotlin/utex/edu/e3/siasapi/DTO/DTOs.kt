package utex.edu.e3.siasapi.DTO

import utex.edu.e3.siasapi.Entity.Rol
import utex.edu.e3.siasapi.Entity.Usuario
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.FetchType
import javax.persistence.OneToMany

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