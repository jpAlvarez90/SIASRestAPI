package utex.edu.e3.siasapi.Service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import utex.edu.e3.siasapi.DTO.RolDTO
import utex.edu.e3.siasapi.Entity.Rol
import utex.edu.e3.siasapi.Entity.Usuario
import utex.edu.e3.siasapi.Repository.RolRepository

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

    fun obtenerUnRol(id:Long): Rol = rolRepository.getById(id)

    fun guardarRol(persona: Rol): Rol = rolRepository.save(persona)

    fun eliminarRol(id:Long) = rolRepository.deleteById(id)

    fun existeRolPorNombre(rol:String):Boolean {
        return rolRepository.existsByRol(rol)
    }

    fun encontrarPorRol(rol:String):Rol{
        return rolRepository.findByRol(rol)
    }

}