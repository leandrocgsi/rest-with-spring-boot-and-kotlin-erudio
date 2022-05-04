#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config

import ${package}.security.jwt.JwtConfigurer
import ${package}.security.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter(){

    @Autowired
    private lateinit var tokenProvider: JwtTokenProvider

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        val encoders: MutableMap<String, PasswordEncoder> = HashMap<String, PasswordEncoder>()
        encoders["pbkdf2"] = Pbkdf2PasswordEncoder()
        val passwordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
        passwordEncoder.setDefaultPasswordEncoderForMatches(Pbkdf2PasswordEncoder())
        return passwordEncoder
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager = super.authenticationManagerBean()

    override fun configure(http: HttpSecurity) {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/auth/signin", "/auth/refresh", "/api-docs/**", "/swagger-ui/index.html**").permitAll()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/users").denyAll()
            .and()
                .cors()
            .and()
            .apply(JwtConfigurer(tokenProvider))
    }
}