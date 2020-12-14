package data

data class Node(
    val jolt: Int,
    var ones: Node? = null,
    var twos: Node? = null,
    var threes: Node? = null
) {
    fun one() = jolt + 1
    fun three() = jolt + 2
    fun two() = jolt + 3
}
