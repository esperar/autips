package esperer.otp

import esperer.otp.proxy.AuthenticationServerProxy
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class UsernamePasswordAuthenticationProvider(
    private val proxy: AuthenticationServerProxy
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication?): Authentication {
       val username: String = authentication!!.name
       val password: String = authentication.credentials.toString()

        proxy.sendAuth(username, password)

        return UsernamePasswordAuthenticationToken(username, password)
    }

    override fun supports(aClass: Class<*>?): Boolean = true
}