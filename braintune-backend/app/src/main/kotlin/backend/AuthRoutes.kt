package backend

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.http.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

fun Application.authRoutes() {
    routing {
        post("/login") {
            val body = call.receive<LoginRequest>()
            val user = users.find { it.username == body.username && it.password == body.password }

            if (user == null) {
                call.respond(HttpStatusCode.Unauthorized, "Credenciales inválidas")
                return@post
            }

            val token = JWT.create()
                .withClaim("username", user.username)
                .withClaim("role", user.role.name)
                .withExpiresAt(Date(System.currentTimeMillis() + 3600_000)) // 1h
                .sign(Algorithm.HMAC256("secret123456"))

            call.respond(LoginResponse(token))
        }
    }
}
