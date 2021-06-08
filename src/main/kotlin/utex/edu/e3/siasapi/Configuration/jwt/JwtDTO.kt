package utex.edu.e3.siasapi.Configuration.jwt

import org.springframework.security.core.GrantedAuthority

data class JwtDTO (
    var token:String,
    var username:String,
    var authorities: MutableCollection<out GrantedAuthority>
) {
    var bearer:String = "Bearer"
}