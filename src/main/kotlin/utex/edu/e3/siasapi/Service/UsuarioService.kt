package utex.edu.e3.siasapi.Service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import utex.edu.e3.siasapi.DTO.UsuarioDTO
import utex.edu.e3.siasapi.Entity.Usuario
import utex.edu.e3.siasapi.Repository.UsuarioRepository
import java.util.*

@Service
class UsuarioService(
    @Autowired private val usuarioRepository: UsuarioRepository,
    @Autowired private val rolService: RolService
    ) {

    fun obtenerUsuarios(): List<UsuarioDTO> {
        val listaUsuario = usuarioRepository.findAll()
        val listaUsuariosDTO: MutableList<UsuarioDTO> = emptyList<UsuarioDTO>().toMutableList()
        listaUsuario.forEach { usuario ->
            val tempDTO = UsuarioDTO(usuario.id,usuario.correo,usuario.estado,usuario.ultimoLogin,usuario.rol!!.rol)
            listaUsuariosDTO += tempDTO
        }
        return listaUsuariosDTO
    }

    fun obtenerUnUsuario(id:Long): UsuarioDTO {
        val usuario: Optional<Usuario> = usuarioRepository.findById(id)
        val tempUsuario: Usuario
        if (usuario.isPresent) {
            tempUsuario = usuario.get()
            return UsuarioDTO(tempUsuario.id,tempUsuario.correo,tempUsuario.estado,tempUsuario.ultimoLogin,tempUsuario.rol!!.rol)
        } else {
            return UsuarioDTO()
        }
    }

    fun obtenerUsuarioPorCorreo (correo:String?) : Usuario? {
        val usuario = usuarioRepository.findByCorreo(correo)
        var tempUsuario:Usuario? = null
        if (usuario.isPresent) {
            tempUsuario = usuario.get()
            return tempUsuario
        }
        return null
    }

    fun guardarUsuario(usuario:Usuario):UsuarioDTO {
        val tempUsuario = usuarioRepository.save(usuario)
        return UsuarioDTO(tempUsuario.id,tempUsuario.correo,tempUsuario.estado,tempUsuario.ultimoLogin,tempUsuario.rol!!.rol)
    }

    //TODO Investigar porque no elimina el usuario
    fun eliminarUsuario(id:Long) {
        print(usuarioRepository.getById(id).correo)
        usuarioRepository.deleteById(id)
        print(usuarioRepository.existsById(id))
    }

    fun existeUsuarioPorCorreo(correo:String):Boolean {
        return usuarioRepository.existsByCorreo(correo)
    }

}