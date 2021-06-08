package utex.edu.e3.siasapi.Entity

import java.util.*
import javax.persistence.*

@Entity
data class Division(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var nombre:String,
)

@Entity
data class Usuario(
    var correo:String,
    var contrasena:String,
    var estado: String,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var ultimoLogin: Date? = null

    @ManyToOne
    @JoinColumn(name = "rol_id")
    var rol: Rol? = null
}

@Entity
data class Rol(
    var rol: String,
    var estado: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null

    @OneToMany(mappedBy = "rol", cascade = arrayOf(CascadeType.ALL))
    var usuarios: List<Usuario> = emptyList()
}