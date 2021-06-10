package utex.edu.e3.siasapi.Service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import utex.edu.e3.siasapi.Entity.Carrera
import utex.edu.e3.siasapi.Repository.CarreraRepository

@Service
class CarreraService(@Autowired val carreraRepository: CarreraRepository) {
    fun findAllCarreras(): MutableIterable<Carrera>{
        return carreraRepository.findAll()
    }
}