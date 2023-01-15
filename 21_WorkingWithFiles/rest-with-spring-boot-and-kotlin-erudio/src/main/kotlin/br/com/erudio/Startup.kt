package br.com.erudio

 import java.util.HashMap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
 import org.springframework.security.crypto.password.DelegatingPasswordEncoder
 import org.springframework.security.crypto.password.PasswordEncoder
 import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder


@SpringBootApplication
class Startup

fun main(args: Array<String>) {
	runApplication<Startup>(*args)
}
