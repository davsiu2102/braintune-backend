package backend

import kotlinx.serialization.Serializable

@Serializable
data class User(val id: Int, val username: String, val pass: String)