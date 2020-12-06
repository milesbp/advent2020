import java.nio.file.Paths

class Day5 {

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
        println("Day5 run 1")
        var highest = 0
        processInput().forEach { rows ->
            val row = convertToBinary(rows.substring(0, 7), 'F', 'B')
            val col = convertToBinary(rows.substring(7), 'L', 'R')
            if (row + col > highest) {
                highest = row * 8 + col
                println(row + col)
            }
            seatMap[row][col] = true
        }

        for (i in seatMap.indices) {
            for (j in 1 until seatMap[i].size) {
                if (!seatMap[i][j]) {
                    println("empty seat at: row $i, col $j")
                    if (seatMap[i][j - 1] && seatMap[i][j + 1]) {
                        println("My seat at: row $i, col $j\"")
                        println(i * 8 + j)
                    }
                }
            }
        }
    }

    private val seatMap = Array(128) { BooleanArray(8) }

    fun processInput() =
        Paths.get(javaClass.classLoader.getResource("day5input.txt")!!.toURI()).toFile().useLines { it.toList() }

    fun convertToBinary(input: String, zero: Char, one: Char): Int {
        val builder = StringBuilder()
        input.forEach { char ->
            when (char) {
                zero -> builder.append('0')
                one -> builder.append('1')
                else -> builder.append('0')

            }
        }
        return Integer.parseInt(builder.toString(), 2)
    }
}



