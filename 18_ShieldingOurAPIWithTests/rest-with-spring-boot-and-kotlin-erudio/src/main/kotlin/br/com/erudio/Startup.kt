package br.com.erudio

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
	val pbkdf2Encoder = Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256)
	encoders["pbkdf2"] = pbkdf2Encoder
	val passwordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
	passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder)

	val result = passwordEncoder.encode("foo-bar")
	println("My hash $result")
	*/
}
