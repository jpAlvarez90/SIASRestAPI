package utex.edu.e3.siasapi.Entity

import java.util.*
import javax.persistence.*

@Entity
data class Division(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var nombre:String,
        var siglas:String,
        var estado: String,

        @OneToMany(mappedBy = "division", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.LAZY)
        var carreras: List<Carrera> = Collections.emptyList(),

        @OneToMany(mappedBy = "divisionEst", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        var estudiantess: List<Estudiante> = Collections.emptyList()
)

@Entity
data class Carrera(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var nombre:String,
        var estado: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "division_id")
        var division: Division? = null,

        @OneToMany(mappedBy = "carrera", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        var estudiantes: List<Estudiante> = Collections.emptyList()
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

    @OneToMany(mappedBy = "usuario", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
    var estudiantes: List<Estudiante> = Collections.emptyList()
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


@Entity
data class Estudiante(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long,
        var nombre: String,
        var primerApellido: String,
        var segundoApellido: String,
        var matricula: String,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "usuario_id")
        var usuario: Usuario? = null,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "carrera_id")
        var carrera: Carrera? = null,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "division_id")
        var divisionEst: Division? = null,
)
