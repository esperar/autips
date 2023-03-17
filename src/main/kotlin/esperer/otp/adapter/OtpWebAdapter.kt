package esperer.otp.adapter

import esperer.otp.Otp
import esperer.otp.usecase.UserUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/otp")
class OtpWebAdapter(
    private val userUseCase: UserUseCase
) {

    @PostMapping("/check")
    fun check(@RequestBody otp: Otp, response: HttpServletResponse){
        if(userUseCase.check(otp)){
            response.status = HttpServletResponse.SC_OK
        } else {
            response.status = HttpServletResponse.SC_FORBIDDEN
        }
    }
}