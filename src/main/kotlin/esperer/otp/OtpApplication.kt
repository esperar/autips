package esperer.otp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OtpApplication

fun main(args: Array<String>) {
	runApplication<OtpApplication>(*args)
}
