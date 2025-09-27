package backend

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.protectedRoutes() {
    routing {
        authenticate("auth-jwt") {
            get("/donors") {
                val principal = call.principal<JWTPrincipal>()
                val role = principal?.payload?.getClaim("role")?.asString()

                if (role == "ADMIN") {
                    call.respond(listOf("Donor A", "Donor B", "Donor C"))
                } else {
                    call.respond("Access denied: only admins can see donors")
                }
            }

            get("/profile") {
                val principal = call.principal<JWTPrincipal>()
                val username = principal?.payload?.getClaim("username")?.asString()
                call.respond("Welcome $username")
            }
        }
    }
}
