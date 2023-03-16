package esperer.otp.adapter

import esperer.otp.principal.User
import esperer.otp.principal.usecase.UserUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserWebAdapter(
    private val userUseCase: UserUseCase
) {

    @PostMapping("/add")
    fun addUser(@RequestBody user: User) {
        userUseCase.addUser(user)
    }

    @PostMapping("/auth")
    fun auth(@RequestBody user: User){
        userUseCase.auth(user)
    }

}