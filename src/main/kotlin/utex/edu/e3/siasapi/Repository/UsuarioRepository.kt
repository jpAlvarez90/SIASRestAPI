package utex.edu.e3.siasapi.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import utex.edu.e3.siasapi.Entity.Usuario

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
}