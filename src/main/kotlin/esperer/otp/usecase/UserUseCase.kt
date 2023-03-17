package esperer.otp.usecase

import esperer.otp.annotation.TransactionalUseCase
import esperer.otp.otp.Otp
import esperer.otp.principal.persistence.OtpRepository
import esperer.otp.principal.persistence.UserRepository
import esperer.otp.principal.User
import esperer.otp.util.GenerateCodeUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder

@TransactionalUseCase
class UserUseCase(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val otpRepository: OtpRepository,
    private val generateCodeUtil: GenerateCodeUtil
) {

    fun addUser(user: User) {
        user.password = passwordEncoder.encode(user.password)
    }

    fun auth(user: User){
        val findUser = userRepository.findByIdOrNull(user.username)
            ?: throw RuntimeException("Not Found User.")

        if(passwordEncoder.matches(user.password, findUser.password))
            throw RuntimeException("Password Mismatch..")

        renewOtp(findUser)
    }

    fun check(otp: Otp): Boolean {
        val findOtp = otpRepository.findByIdOrNull(otp.username) ?: return false

        if(findOtp.code == otp.code)
            return true

        return false
    }

    private fun renewOtp(user: User) {
        val code = generateCodeUtil.generateCode()
        val otp = otpRepository.findByIdOrNull(user.username)

        if(otp == null)
            otpRepository.save(Otp(user.username, code))
        else {
            otp.code = code
        }
    }

}