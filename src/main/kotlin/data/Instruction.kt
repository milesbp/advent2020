package data

data class Instruction(
    var previous: Int,
    var move: String,
    val accumulator: Int,
)
