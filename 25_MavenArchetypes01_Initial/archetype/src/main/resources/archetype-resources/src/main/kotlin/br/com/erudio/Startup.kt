#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}

// import java.util.HashMap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
// import org.springframework.security.crypto.password.DelegatingPasswordEncoder
// import org.springframework.security.crypto.password.PasswordEncoder
// import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder


@SpringBootApplication
class Startup

fun main(args: Array<String>) {
	runApplication<Startup>(*args)

	/**
	val encoders: MutableMap<String, PasswordEncoder> = HashMap()
	encoders["pbkdf2"] = Pbkdf2PasswordEncoder()
	val passwordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
	passwordEncoder.setDefaultPasswordEncoderForMatches(Pbkdf2PasswordEncoder())

	val result = passwordEncoder.encode("foo-bar")
	println("My hash $result")
	*/
}
