package utex.edu.e3.siasapi.Controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import utex.edu.e3.siasapi.Entity.Estudiante
import utex.edu.e3.siasapi.Service.EstudianteService

@RestController
@RequestMapping("/estudiante")
class EstudianteController(@Autowired val estudianteService: EstudianteService) {

    @GetMapping("/obtener-estudiantes")
    fun obtenerEstudiantes() = estudianteService.obtenerEstudiantes()

    @PostMapping("/guardar-estudiante")
    fun guardarEstudiante(@RequestBody estudiante: Estudiante) = estudianteService.guardadEstudiante(estudiante)
}