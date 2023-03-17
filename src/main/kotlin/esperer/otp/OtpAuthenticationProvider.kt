package esperer.otp

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication

class OtpAuthenticationProvider(
    private val proxy: AuthenticationServerProxy
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication?): Authentication {
        val username = authentication!!.name
        val code = authentication.credentials.toString()

        val result = proxy.sendOTP(username, code)

        return if(result){
            OtpAuthentication(username, code)
        } else {
            throw BadCredentialsException("Bad Credentials.")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean = true
}