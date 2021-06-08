package utex.edu.e3.siasapi.Service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import utex.edu.e3.siasapi.DTO.EstudianteDTO
import utex.edu.e3.siasapi.Entity.Estudiante
import utex.edu.e3.siasapi.Repository.CarreraRepository
import utex.edu.e3.siasapi.Repository.DivisionRepository
import utex.edu.e3.siasapi.Repository.EstudianteRepository
import utex.edu.e3.siasapi.Repository.UsuarioRepository

@Service
class EstudianteService(
        @Autowired private val estudianteRepository: EstudianteRepository,
        @Autowired private val divisionRepository: DivisionRepository,
        @Autowired private val usuarioRepository: UsuarioRepository,
        @Autowired private val carreraRepository: CarreraRepository
) {
    fun obtenerEstudiantes(): List<EstudianteDTO>{
        val listaEstudiante = estudianteRepository.findAll()
        val listaEstudianteDTO: MutableList<EstudianteDTO> = emptyList<EstudianteDTO>().toMutableList()
        listaEstudiante.forEach{
            estudiante -> val tempDTO = EstudianteDTO(estudiante.id, estudiante.nombre, estudiante.primerApellido,
                estudiante.segundoApellido,estudiante.matricula,estudiante.usuario!!.correo, estudiante.carrera!!.nombre,
                estudiante.divisionEst!!.nombre)
            listaEstudianteDTO += tempDTO
        }
        return listaEstudianteDTO
    }

    fun guardadEstudiante(estudiante: Estudiante):EstudianteDTO{
        val tempEstudiante = estudianteRepository.save(estudiante)
        return EstudianteDTO(estudiante.id, estudiante.nombre, estudiante.primerApellido,
                estudiante.segundoApellido,estudiante.matricula,estudiante.usuario!!.correo, estudiante.carrera!!.nombre,
                estudiante.divisionEst!!.nombre)
    }
}