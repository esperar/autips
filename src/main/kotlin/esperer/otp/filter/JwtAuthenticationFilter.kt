package esperer.otp.filter

import io.jsonwebtoken.security.Keys
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
        request.getHeader("Authorization")
        Keys.hmacShaKeyFor(
            signingKey.toByteArray(StandardCharsets.UTF_8)
        )
    }
}