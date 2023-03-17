### Spring security in Action

> 스프링 시큐리티 인 액션 otp 인증 시스템
> Kotlin 마이그레이션
  
```xml
<dependencies>
	<!-- ... other dependency elements ... -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
	</dependency>
</dependencies>
```

**Todo**
- AuthenticationServerProxy 구축
- Username, Otp AuthenticationProvider 생성
- supports 관련 expression 수정
- initialAuthentication, JwtAuthenticationFilter 재작성
- restTemplate setting

[참고자료](http://www.yes24.com/Product/Goods/112200347)
[Spring Security Docs](https://docs.spring.io/spring-security/reference/index.html)