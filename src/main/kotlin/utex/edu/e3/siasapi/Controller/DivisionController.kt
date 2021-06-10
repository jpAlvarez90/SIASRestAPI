package utex.edu.e3.siasapi.Controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import utex.edu.e3.siasapi.Entity.Division
import utex.edu.e3.siasapi.Service.DivisionService

@RestController
@RequestMapping("/divisiones")
class DivisionController (@Autowired val divisonService: DivisionService) {

    @GetMapping("/consultar-todas")
    fun consultarDivisiones() = divisonService.findAllDivisiones()


    @PostMapping("/crear-division")
    fun crearDivision(@RequestBody divison: Division): Division {
        return divisonService.crearDivision(divison)
    }

}