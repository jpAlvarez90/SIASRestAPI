package utex.edu.e3.siasapi.Repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import utex.edu.e3.siasapi.Entity.Carrera

@Repository
interface CarreraRepository:CrudRepository<Carrera,Long> {
}