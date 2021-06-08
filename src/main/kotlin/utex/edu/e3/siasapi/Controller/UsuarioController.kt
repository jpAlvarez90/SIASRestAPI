package utex.edu.e3.siasapi.Controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import utex.edu.e3.siasapi.DTO.UsuarioDTO
import utex.edu.e3.siasapi.Entity.Usuario
import utex.edu.e3.siasapi.Service.UsuarioService

@RestController
@RequestMapping("/usuario")
class UsuarioController(@Autowired val usuarioService: UsuarioService) {

    @GetMapping("/obtener-usuarios")
    fun obtenerUsuarios() = usuarioService.obtenerUsuarios()

    @GetMapping("/obtener-usuario-especifico/{id}")
    fun obtenerUsuarioEspecifico(@PathVariable id:Long) = usuarioService.obtenerUnUsuario(id)

    @PostMapping("/guardar-usuario")
    fun guardarUsuario(@RequestBody usuario:Usuario) = usuarioService.guardarUsuario(usuario)

    @DeleteMapping("/eliminar-usuario/{id}")
    fun eliminarUsuario(@PathVariable id:Long) = usuarioService.eliminarUsuario(id)

}