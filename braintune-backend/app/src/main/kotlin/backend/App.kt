package backend

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.routing.*

class App // Puedes dejar la clase vacía o eliminarla si no la necesitas

// Usuarios hardcodeados
val users = listOf(
    User("admin", "1234", Role.ADMIN),
    User("david", "abcd", Role.USER)
)

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

// Función que configura Ktor y JWT
fun Application.module() {
    install(ContentNegotiation) { json() }

    install(Authentication) {
        jwt("auth-jwt") {
            realm = "ktor-sample"
            verifier(
                JWT.require(Algorithm.HMAC256("secret123456"))
                    .withIssuer("ktor.io")
                    .build()
            )
            validate { credential ->
                val username = credential.payload.getClaim("username").asString()
                if (username != null) JWTPrincipal(credential.payload) else null
            }
        }
    }

    this@module.authRoutes()
    this@module.protectedRoutes()
}