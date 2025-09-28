package backend

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(val token: String)

@Serializable
data class LoginRequest(val username: String, val password: String)