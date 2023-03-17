### Spring security in Action

> 스프링 시큐리티 인 액션 otp 인증 시스템
> Kotlin 마이그레이션
  

dependency
```gradle

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
    implementation("org.glassfish.jaxb:jaxb-runtime:4.0.2")
```

<br>

**Todo**
- AuthenticationServerProxy 구축
- Username, Otp AuthenticationProvider 생성
- supports 관련 expression 수정
- initialAuthentication, JwtAuthenticationFilter 재작성
- restTemplate setting
- Jwt 파싱, Key 서명
- user, otp usecase, persistence
- generateCodeUtil

[참고자료](http://www.yes24.com/Product/Goods/112200347)  

[Spring Security Docs](https://docs.spring.io/spring-security/reference/index.html)