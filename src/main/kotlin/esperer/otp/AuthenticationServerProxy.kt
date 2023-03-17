package esperer.otp

import esperer.otp.principal.User
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class AuthenticationServerProxy(
    private val rest: RestTemplate,
) {
    val baseUrl: String = "https://localhost:8080"

    fun sendAuth(username: String, password: String){

        val url: String = "$baseUrl/user/auth"

        val body = User(username, password, null)

        val request = HttpEntity(body)

        rest.postForEntity(url, request, Unit.javaClass)
    }

    fun sendOTP(username: String, code: String): Boolean{
        val url: String = "$baseUrl/otp/check"

        val body = User(username, null, code)

        val request = HttpEntity(body)
        val response = rest.postForEntity(url, request, Unit.javaClass)

        return response.statusCode == HttpStatus.OK

    }
}