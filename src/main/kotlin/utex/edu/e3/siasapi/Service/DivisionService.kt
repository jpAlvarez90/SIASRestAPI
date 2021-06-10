package utex.edu.e3.siasapi.Service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import utex.edu.e3.siasapi.DTO.DivisionDTO
import utex.edu.e3.siasapi.Entity.Division
import utex.edu.e3.siasapi.Repository.DivisionRepository

@Service
class DivisionService(@Autowired val divisionRepository: DivisionRepository){

    fun findAllDivisiones(): List<DivisionDTO> {
        val listaDivs = divisionRepository.findAll()
        val listDto: MutableList<DivisionDTO> = emptyList<DivisionDTO>().toMutableList()
        listaDivs.forEach{
            division -> val tempDTO = DivisionDTO(division.id,division.nombre,division.siglas)
            listDto += tempDTO
        }
        return listDto
    }

    fun crearDivision(division: Division): Division {
        return divisionRepository.save(division)
    }

    //fun findAllDivisiones(): MutableIterable<Divison> = divisionRepository.findAll()

}