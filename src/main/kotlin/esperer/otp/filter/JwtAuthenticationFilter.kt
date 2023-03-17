package esperer.otp.filter

import esperer.otp.UsernamePasswordAuthentication
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter: OncePerRequestFilter() {

    val signingKey = "ASDFasdjfansdkvasdFAFF2345$#@#$%^"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val jwt = request.getHeader("Authorization")

        val key = Keys.hmacShaKeyFor(
            signingKey.toByteArray(StandardCharsets.UTF_8)
        )

        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwt)
            .body

        val username = claims.get("username").toString()

        val a = SimpleGrantedAuthority("user")
        val auth = UsernamePasswordAuthentication(username, null, listOf(a))

        SecurityContextHolder.getContext().authentication = auth
        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean =
        request.servletPath != "/login"
}