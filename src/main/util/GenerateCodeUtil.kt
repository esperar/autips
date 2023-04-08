package esperer.otp.util

import org.springframework.stereotype.Component
import java.lang.RuntimeException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom


@Component
class GenerateCodeUtil {
    fun generateCode(): String{
        val code: String

        try{
            val secureRandom = SecureRandom.getInstanceStrong()
            val c = secureRandom.nextInt(9000) + 1000
            code = c.toString()
        } catch (e: NoSuchAlgorithmException){
            throw RuntimeException("Problem when generating the random code")
        }

        return code
    }
}