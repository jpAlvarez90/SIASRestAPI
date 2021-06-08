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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var correo:String,
    var contrasena:String,
    var estado: String,
    var ultimoLogin: Date,

    @ManyToOne
    @JoinColumn(name = "rol_id")
    var rol: Rol? = null
)

@Entity
data class Rol(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,
    var rol: String,
    var estado: String,

    @OneToMany(mappedBy = "rol", cascade = arrayOf(CascadeType.ALL))
    var usuarios: List<Usuario> = emptyList()
)
