package utex.edu.e3.siasapi.Controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import utex.edu.e3.siasapi.DTO.RolDTO
import utex.edu.e3.siasapi.Entity.Rol
import utex.edu.e3.siasapi.Service.RolService

@RestController
@RequestMapping("/rol")
class RolController(@Autowired val rolService: RolService) {

    @GetMapping("/obtener-roles")
    fun obtenerRoles() = rolService.obtenerRoles()

    @GetMapping("/obtener-rol-especifico/{id}")
    fun obtenerRolEspecifico(@PathVariable id:Long) = rolService.obtenerUnRol(id)

    @PostMapping("/guardar-rol")
    fun guardarRol(@RequestBody rol:Rol) = rolService.guardarRol(rol)

    @DeleteMapping("/eliminar-rol/{id}")
    fun eliminarRol(@PathVariable id:Long) = rolService.eliminarRol(id)

}