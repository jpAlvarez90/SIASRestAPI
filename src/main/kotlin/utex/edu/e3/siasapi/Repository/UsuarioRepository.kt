package utex.edu.e3.siasapi.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import utex.edu.e3.siasapi.Entity.Usuario
import java.util.*

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {

    fun findByCorreo(correo:String?) : Optional<Usuario>

    fun existsByCorreo(correo: String?) : Boolean

}