package utex.edu.e3.siasapi.Configuration.jwt

import javassist.bytecode.ExceptionTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.filter.OncePerRequestFilter
import utex.edu.e3.siasapi.Configuration.UserDetailsCustom
import utex.edu.e3.siasapi.Configuration.UserDetailsServiceCustom
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter (): OncePerRequestFilter() {

    @Autowired
    private lateinit var jwtProvider: JwtProvider
    @Autowired
    private lateinit var userDetailsService: UserDetailsServiceCustom

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val header:String? = request.getHeader("Authorization")
            var token:String? = null

            if (header != null && header.startsWith("Bearer")) {
               token = header.replace("Bearer ","")
            }

            if (token != null && jwtProvider.verifyToken(token)) {
                val username:String = jwtProvider.getUsernameFromToken(token)
                val userDetails:UserDetails = userDetailsService.loadUserByUsername(username)
                val auth = UsernamePasswordAuthenticationToken(username,null,userDetails.authorities)
                SecurityContextHolder.getContext().authentication = auth
            }
        } catch (e:Exception) {
            println("Un error ocurrio en el request filter")
        }
        filterChain.doFilter(request,response)
    }
}