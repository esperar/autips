package esperer.otp

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Otp(

    @Id
    val username: String,
    var code: String
) {
}