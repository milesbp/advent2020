import java.nio.file.Paths

class Day11 {


    fun run1() {
        var seats = mutableListOf<String>()
        seats.add(".".repeat(processAdapters[0].length + 2))
        processAdapters.forEach { seats.add(".$it.") }
        seats.add(".".repeat(processAdapters[0].length + 2))
        var moved = true
        var moveCount = 0
        val moves = mutableListOf<Pair<Int, Int>>()
        while (moved) {
            for (i in 1..seats.size - 2) {
                for (j in 1..seats[i].length - 2) {
                    when (seats[i][j]) {
                        'L', '#' -> {
                            checkSurrounding(i, j, seats[i][j], seats)?.let {
                                moves.add(it)
                            }
                        }
                    }
                }
            }
            //if (moves.isNotEmpty()) {
                seats = swapSeats(seats, moves)
                printSeats(seats)
            println()
                if (moves.size == moveCount)
                    moved = false
                else {
                    moveCount = moves.size
                    moves.clear()
                }
            //}
        }
        val totalOccupied = seats.map {
            it.filter {
                it == '#'
            }.length
        }.sum()
        println(totalOccupied)

    }

    private fun printSeats(ferry: MutableList<String>) =
        ferry.forEach {
            println(it)
        }


    private fun swapSeats(ferry: MutableList<String>, seats: List<Pair<Int, Int>>): MutableList<String> {
        seats.forEach {
            if (ferry[it.first][it.second] == '#')
                ferry[it.first].toCharArray().let { chars ->
                    chars[it.second] = 'L'
                    ferry[it.first] = String(chars)
                }
            else
                ferry[it.first].toCharArray().let { chars ->
                    chars[it.second] = '#'
                    ferry[it.first] = String(chars)
                }
        }
        return ferry
    }

    private fun checkSurrounding(i: Int, j: Int, seat: Char, ferry: List<String>): Pair<Int, Int>? {
        var occupied = 0
        for (ii in i - 1..i + 1) {
            for (jj in j - 1..j + 1) {
                if(i == 1 && j == 3)
                    println()
                if (ferry[ii][jj] == '#' && !(ii == i && jj == j))
                    occupied++
            }
        }
        when (seat) {
            'L' -> {
                if (occupied == 0)
                    return Pair(i, j)
            }
            '#' -> {
                if (occupied > 3)
                    return Pair(i, j)
            }
        }
        return null
    }


    private fun checkSurroundingWithRange(i: Int, j: Int, seat: Char, ferry: List<String>): Pair<Int, Int>? {
        var occupied = 0
        for (ii in i - 1..i + 1) {
            for (jj in j - 1..j + 1) {
                if(i == 1 && j == 3)
                    println()
                if (ferry[ii][jj] == '#' && !(ii == i && jj == j))
                    occupied++
            }
        }
        when (seat) {
            'L' -> {
                if (occupied == 0)
                    return Pair(i, j)
            }
            '#' -> {
                if (occupied > 5)
                    return Pair(i, j)
            }
        }
        return null
    }
    private val processAdapters =
        Paths.get(javaClass.classLoader.getResource("day11input.txt")!!.toURI()).toFile()
            .useLines { it.toList() }
}
