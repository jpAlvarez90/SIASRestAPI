package utex.edu.e3.siasapi.Controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import utex.edu.e3.siasapi.Entity.Carrera
import utex.edu.e3.siasapi.Service.CarreraService

@RestController
@RequestMapping("/carreras")
class CarreraController (@Autowired val carreraService: CarreraService){
    @GetMapping("/consultar-todas")
    fun consultarCarreras(): MutableIterable<Carrera>{
        return carreraService.findAllCarreras()
    }
}