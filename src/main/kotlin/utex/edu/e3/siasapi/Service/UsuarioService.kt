package utex.edu.e3.siasapi.Service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import utex.edu.e3.siasapi.DTO.UsuarioDTO
import utex.edu.e3.siasapi.Entity.Usuario
import utex.edu.e3.siasapi.Repository.UsuarioRepository
import java.util.*

@Service
class UsuarioService(@Autowired private val usuarioRepository: UsuarioRepository) {

    fun obtenerUsuarios(): List<UsuarioDTO> {
        val listaUsuario = usuarioRepository.findAll()
        val listaUsuariosDTO: MutableList<UsuarioDTO> = emptyList<UsuarioDTO>().toMutableList()
        listaUsuario.forEach { usuario ->
            val tempDTO = UsuarioDTO(usuario.id,usuario.correo,usuario.estado,usuario.ultimoLogin,usuario.rol!!.rol)
            listaUsuariosDTO += tempDTO
        }
        return listaUsuariosDTO
    }

    fun obtenerUnUsuario(id:Long): ResponseEntity<UsuarioDTO> {
        val usuario: Optional<Usuario> = usuarioRepository.findById(id)
        val tempUsuario: Usuario
        if (usuario.isPresent) {
            tempUsuario = usuario.get()
            val usuarioDTO = UsuarioDTO(tempUsuario.id,tempUsuario.correo,tempUsuario.estado,tempUsuario.ultimoLogin,tempUsuario.rol!!.rol)
            return ResponseEntity.ok(usuarioDTO)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    fun guardarUsuario(usuario:Usuario):UsuarioDTO {
        val tempUsuario = usuarioRepository.save(usuario)
        return UsuarioDTO(tempUsuario.id,tempUsuario.correo,tempUsuario.estado,tempUsuario.ultimoLogin,tempUsuario.rol!!.rol)
    }

    fun eliminarUsuario(id:Long): ResponseEntity<Boolean> {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id)
            return ResponseEntity.ok(true)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

}