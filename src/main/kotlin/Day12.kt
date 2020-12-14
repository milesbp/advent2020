import java.nio.file.Paths
import kotlin.math.absoluteValue

class Day12 {
    private val DEGREES = 90

    private val directions = listOf('N', 'E', 'S', 'W')
    private var facing = 'E'
    val moves = hashMapOf<Char, Int>('N' to 0, 'E' to 0, 'S' to 0, 'W' to 0)
    fun run1() {
        val cardinal = Pair(0, 0)
        processMoves.forEach {
            val action = it[0]
            val value = it.takeLast(it.length - 1).toInt()
            moveShip(Pair(action, value))
        }
        println("NS ${moves['N']!! - moves['S']!!}")
        println("NS ${moves['E']!! - moves['W']!!}")
        println((moves['N']!! - moves['S']!!).absoluteValue + (moves['E']!! - moves['W']!!).absoluteValue)
    }


    fun moveShip(move: Pair<Char, Int>) {
        println("${move.first}, ${move.second}")
        when (move.first) {
            'F' -> moves[facing] = moves[facing]!! + move.second
            'L' -> facing = directions[Math.floorMod(directions.indexOf(facing) - move.second / 90, 4)]
            'R' -> facing = directions[(directions.indexOf(facing) + move.second / 90) % 4]
            else -> moves[move.first] = moves[move.first]!! + move.second
        }
    }

    private val processMoves =
        Paths.get(javaClass.classLoader.getResource("day12input.txt")!!.toURI()).toFile()
            .useLines { it.toList() }
}
