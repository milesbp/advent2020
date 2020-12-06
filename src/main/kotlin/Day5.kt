import java.nio.file.Paths

class Day5 {

    private val seatMap = Array(128) { BooleanArray(8) }

    fun run1() {
        println("Day5 run 1")
        var highest = 0
        processInput().forEach { rows ->
            val row = convertToBinary(rows.substring(0, 7), 'F', 'B') * 8
            val col = convertToBinary(rows.substring(7), 'L', 'R')
            if (row + col > highest) {
                highest = row + col
                println(row + col)
            }
        }
        println("last highest: $highest")
    }

    fun run2() {
        println("Day5 run 2")
        setupSeatMap()
        for (i in seatMap.indices) {
            for (j in 1 until seatMap[i].size) {
                if (!seatMap[i][j]) {
                    //println("empty seat at: row $i, col $j")
                    if (seatMap[i][j - 1] && seatMap[i][j + 1]) {
                        println("My seat at: row $i, col $j\"")
                        println(i * 8 + j)
                    }
                }
            }
        }
    }

    private fun setupSeatMap() =
        processInput().forEach { rows ->
            val row = convertToBinary(rows.substring(0, 7), 'F', 'B')
            val col = convertToBinary(rows.substring(7), 'L', 'R')
            seatMap[row][col] = true
        }

    fun processInput() =
        Paths.get(javaClass.classLoader.getResource("day5input.txt")!!.toURI()).toFile().useLines { it.toList() }

    fun convertToBinary(input: String, zero: Char, one: Char): Int =
        Integer.parseInt(StringBuilder().also { builder ->
            input.forEach { char ->
                when (char) {
                    zero -> builder.append('0')
                    one -> builder.append('1')
                    else -> builder.append('0')
                }
            }
        }.toString(), 2)
}
