package esperer.otp

import esperer.otp.filter.InitialAuthenticationFilter
import esperer.otp.filter.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val initializerAuthenticationFilter: InitialAuthenticationFilter,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val otpAuthenticationProvider: OtpAuthenticationProvider,
    private val usernamePasswordAuthenticationProvider: UsernamePasswordAuthenticationProvider,
) {

    protected fun configure(auth: AuthenticationManagerBuilder){
        auth.authenticationProvider(otpAuthenticationProvider)
            .authenticationProvider(usernamePasswordAuthenticationProvider)
    }

    protected fun configure(http: HttpSecurity){
        http.csrf().disable()
        http.addFilterAt(initializerAuthenticationFilter,
            BasicAuthenticationFilter::class.java)
            .addFilterAfter(jwtAuthenticationFilter,
                BasicAuthenticationFilter::class.java)

        http.authorizeRequests()
            .anyRequest()
            .authenticated()
    }

    @Bean
    protected fun authenticationManager(): AuthenticationManager =
        authenticationManager()
}