package utex.edu.e3.siasapi.Repository

import org.springframework.data.repository.CrudRepository
import utex.edu.e3.siasapi.Entity.Estudiante

interface EstudianteRepository:CrudRepository<Estudiante,Long> {
}