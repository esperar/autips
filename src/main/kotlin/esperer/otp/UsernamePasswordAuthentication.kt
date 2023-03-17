package esperer.otp

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class UsernamePasswordAuthentication : UsernamePasswordAuthenticationToken {

    constructor(principal: Any?, credential: Any?): super(principal, credential)

    constructor(principal: Any?, credential: Any?, authorities: Collection<out GrantedAuthority>)
            : super(principal, credential, authorities)
}