package utex.edu.e3.siasapi.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import utex.edu.e3.siasapi.Entity.Rol

@Repository
interface RolRepository: JpaRepository<Rol, Long> {
}