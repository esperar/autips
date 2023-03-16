package esperer.otp.otp

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Otp(

    @Id
    val username: String,
    val code: String
) {
}