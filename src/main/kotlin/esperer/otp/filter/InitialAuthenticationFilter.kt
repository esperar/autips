package esperer.otp.filter

import com.sun.org.apache.xml.internal.security.utils.XMLUtils.getBytes
import esperer.otp.OtpAuthentication
import esperer.otp.UsernamePasswordAuthentication
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class InitialAuthenticationFilter(
    private val manager: AuthenticationManager

): OncePerRequestFilter() {

    private val signingKey = "YmlafAMSDFALSEJFMlfasjdofae@#$%^sdfmasdloiefjasd"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val username = request.getHeader("username")
        val password = request.getHeader("password")
        val code: String? = request.getHeader("code")

        if(code == null){
            val auth: Authentication = UsernamePasswordAuthentication(username, password)
            manager.authenticate(auth)
        } else {
            val auth = OtpAuthentication(username, code)
            val a = manager.authenticate(auth)

            val key = Keys.hmacShaKeyFor(
                signingKey.toByteArray()
            )

            val jwt = Jwts.builder()
                .setClaims(mapOf("username" to username))
                .signWith(key)
                .compact()

            response.setHeader("Authorization", jwt)

        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean =
        request.servletPath != "/login"
}