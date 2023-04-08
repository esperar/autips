package esperer.otp.principal.persistence

import esperer.otp.Otp
import org.springframework.data.jpa.repository.JpaRepository

interface OtpRepository: JpaRepository<Otp, String> {
}