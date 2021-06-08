package utex.edu.e3.siasapi.Configuration.jwt

import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import utex.edu.e3.siasapi.Configuration.UserDetailsCustom
import java.util.*

@Component
class JwtProvider {

    @Value("\${jwt.passWordSecret}")
    lateinit var passwordSecret:String

    @Value("\${jwt.expiration}")
    var expiration:Int = 0

    var errorMsg: String = ""

    fun generateToken(auth:Authentication): String {
        val userDetailsCustom:UserDetailsCustom = auth.principal as UserDetailsCustom
        val encodedString:String = Base64.getEncoder().encodeToString(passwordSecret.encodeToByteArray())
        return Jwts.builder()
            .setSubject(userDetailsCustom.user)
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + expiration * 1000))
            .signWith(SignatureAlgorithm.HS512, encodedString)
            .compact()
    }

    fun getUsernameFromToken(token:String): String {
        return Jwts.parser().setSigningKey(this.passwordSecret).parseClaimsJws(token).body.subject
    }

    fun verifyToken(token:String): Boolean {
        try {
            Jwts.parser().setSigningKey(this.passwordSecret).parseClaimsJws(token)
            return true
        }catch (e:MalformedJwtException) {
            errorMsg = "Token mal formado.";
            println(errorMsg)
        }catch (e:UnsupportedJwtException) {
            errorMsg = "Token no soportado.";
            println(errorMsg)
        }catch (e:ExpiredJwtException) {
            errorMsg = "Token expirado.";
            println(errorMsg)
        }catch (e:IllegalArgumentException) {
            errorMsg = "No hay token.";
            println(errorMsg)
        }catch (e:SignatureException) {
            errorMsg = "Firma errónea.";
            println(errorMsg)
        }
        return false
    }

}