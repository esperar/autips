### Spring security in Action

> Spring Security Otp System migrate to Kotlin  
> 스프링 시큐리티 OTP 시스템 마이그레이션 to 코틀린


<br>

SEND OTP Part
```kotlin
 fun sendAuth(username: String, password: String){
        val url = "$baseUrl/user/auth"
        val body = User(username, password, null)
        val request = HttpEntity(body)

        rest.postForEntity(url, request, Unit.javaClass)
    }

    fun sendOTP(username: String, code: String): Boolean{
        val url = "$baseUrl/otp/check"
        val body = User(username, null, code)
        val request = HttpEntity(body)
        val response = rest.postForEntity(url, request, Unit.javaClass)

        return response.statusCode == HttpStatus.OK

    }
```

Authenticated Part

```kotlin
override fun authenticate(authentication: Authentication?): Authentication {
        val username = authentication!!.name
        val code = authentication.credentials.toString()

        val result = proxy.sendOTP(username, code)

        return if(result){
            OtpAuthentication(username, code)
        } else {
            throw BadCredentialsException("Bad Credentials.")
        }
    }
```

<br>
#### RUN EXAM
```sh
curl -H "username:esperer" -H "password:12345" http://localhost8080/login
```

```
Response: {
 Username: esperer
 Code: xxxx
}
```

```sh
curl -v -H "username: esperer" -H "code:xxxx" http:/./localhost:8080/login
```

```
Response: {
< Http/1.1 200
< Authorization: ey...
}
```

```sh
curl -H "Authorization: ey..." http:/./localhost:8080/test
```

```
test
```


<br>


```
- AuthenticationServerProxy,
- Username, Otp AuthenticationProvider
- supports expression -> XXXXX
- initialAuthentication, JwtAuthenticationFilter
- restTemplate setting
- Parse Jwt, Keys
- user, otp usecase, persistence
- generateCodeUtil impl
```

<br>

[reference](http://www.yes24.com/Product/Goods/112200347)
[Security Docs](https://docs.spring.io/spring-security/reference/index.html)
