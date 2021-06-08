package utex.edu.e3.siasapi.Configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import utex.edu.e3.siasapi.Entity.Usuario
import utex.edu.e3.siasapi.Service.UsuarioService
import javax.transaction.Transactional

@Service
@Transactional
class UserDetailsServiceCustom(@Autowired val usuarioService: UsuarioService): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {

        var usuarioEntity: Usuario? = usuarioService.obtenerUsuarioPorCorreo(username)

        if (usuarioEntity == null) {
            throw UsernameNotFoundException("Usuario no encontrado :D")
        }

        return UserDetailsCustom.builds(usuarioEntity)

    }
}