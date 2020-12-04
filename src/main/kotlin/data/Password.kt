package data

data class Password(
    val min: Int,
    val max: Int,
    val character: Char,
    val password: String
)
