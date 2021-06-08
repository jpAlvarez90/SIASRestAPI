package utex.edu.e3.siasapi.Service

import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import utex.edu.e3.siasapi.DTO.RolDTO
import utex.edu.e3.siasapi.Entity.Rol
import utex.edu.e3.siasapi.Entity.Usuario
import utex.edu.e3.siasapi.Repository.RolRepository
import java.util.*

@Service
class RolService(@Autowired val rolRepository: RolRepository) {

    fun obtenerRoles(): List<RolDTO> {
        val listaRoles = rolRepository.findAll()
        val listaRolesDTO: MutableList<RolDTO> = emptyList<RolDTO>().toMutableList()
        listaRoles.forEach { rol ->
            val tempRol = RolDTO(rol.id,rol.rol,rol.estado)
            listaRolesDTO += tempRol
        }
        return listaRolesDTO
    }

    fun obtenerUnRol(id:Long): ResponseEntity<RolDTO> {
        val rol: Optional<Rol> = rolRepository.findById(id)
        val tempRol: Rol
        if (rol.isPresent) {
            tempRol = rol.get()
            val rolDTO = RolDTO(tempRol.id,tempRol.rol,tempRol.estado)
            return ResponseEntity.ok(rolDTO)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    fun guardarRol(persona: Rol): RolDTO {
        val tempRol = rolRepository.save(persona)
        return RolDTO(tempRol.id,tempRol.rol,tempRol.estado)
    }

    fun eliminarRol(id:Long): ResponseEntity<Boolean> {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id)
            return ResponseEntity.ok(true)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    fun existeRolPorNombre(rol:String):Boolean {
        return rolRepository.existsByRol(rol)
    }

    fun encontrarPorRol(rol:String):Rol{
        return rolRepository.findByRol(rol)
    }

}