package esperer.otp

import esperer.otp.filter.InitialAuthenticationFilter
import esperer.otp.filter.JwtAuthenticationFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val initializerAuthenticationFilter: InitialAuthenticationFilter,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val otpAuthenticationProvider: OtpAuth
) {
}