package esperer.otp.principal.persistence

import esperer.otp.principal.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {
}