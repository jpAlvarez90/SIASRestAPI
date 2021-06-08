package utex.edu.e3.siasapi.Configuration

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import utex.edu.e3.siasapi.Entity.Usuario
import java.io.Serializable

open class UserDetailsCustom: UserDetails, Serializable {

    var user: String = ""
    var pass:String = ""
    var auths: MutableCollection<out GrantedAuthority> = emptyList<GrantedAuthority>().toMutableList()

    constructor(): super()

    constructor(user:String, pass:String, auths:MutableCollection<out GrantedAuthority>): super() {
        this.user = user
        this.pass = pass
        this.auths = auths
    }

    companion object {
        fun builds(usuario:Usuario): UserDetailsCustom {
            val auths_list: MutableList<GrantedAuthority> = emptyList<GrantedAuthority>().toMutableList()
            val anAuthorities: SimpleGrantedAuthority = SimpleGrantedAuthority(usuario.rol!!.rol)
            auths_list.add(anAuthorities)

            return UserDetailsCustom(usuario.correo, usuario.contrasena, auths_list)
        }
    }



    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.auths
    }

    override fun getPassword(): String {
        return this.pass
    }

    override fun getUsername(): String {
        return this.user
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}