package utex.edu.e3.siasapi.Controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import utex.edu.e3.siasapi.Configuration.jwt.JwtDTO
import utex.edu.e3.siasapi.Configuration.jwt.JwtProvider
import utex.edu.e3.siasapi.DTO.AuthenticationDTO
import utex.edu.e3.siasapi.Entity.Usuario
import utex.edu.e3.siasapi.Service.UsuarioService
import java.util.*

@RestController
class AuthenticationController(
    @Autowired val authenticationManager: AuthenticationManager,
    @Autowired val jwtProvider: JwtProvider,
    @Autowired val usuarioService: UsuarioService
) {

    @PostMapping("/login")
    fun authentication(
        @RequestBody authenticationDTO: AuthenticationDTO
    ): ResponseEntity<*> {
        try {
            val usuario: Usuario? = usuarioService.obtenerUsuarioPorCorreo(authenticationDTO.usuario)
            if (usuario != null && usuario.estado.equals("Activo")) {
                val authentication: Authentication = authenticationManager
                    .authenticate(
                        UsernamePasswordAuthenticationToken(authenticationDTO.usuario, authenticationDTO.contra)
                    )

                SecurityContextHolder.getContext().authentication = authentication

                val token:String = jwtProvider.generateToken(authentication)
                val userDeatils: UserDetails = authentication.principal as UserDetails

                val username: String = SecurityContextHolder.getContext().authentication.name

                val tempUsuario: Usuario = usuarioService.obtenerUsuarioPorCorreo(username)!!
                tempUsuario.ultimoLogin = Date()

                usuarioService.guardarUsuario(tempUsuario)

                val dto = JwtDTO(token, userDeatils.username, userDeatils.authorities)

                return ResponseEntity.ok(dto)
            } else {
                return ResponseEntity("Usuario No existente",HttpStatus.FORBIDDEN)
            }
        } catch (e: BadCredentialsException) {
            return ResponseEntity("Usuario y/o contraseña incorrectos", HttpStatus.BAD_REQUEST)
        }
    }

}