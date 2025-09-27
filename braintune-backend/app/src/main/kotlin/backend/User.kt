package backend

enum class Role { ADMIN, USER }

data class User(
    val username: String,
    val password: String, // para prototipo en claro; en producción usar hash
    val role: Role
)


