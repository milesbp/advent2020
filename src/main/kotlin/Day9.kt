import java.nio.file.Paths

class Day9 {

    private val windowSize = 25
    private val error = 41682220L

    fun run1() {
        val input = processInstructions()
        val windows = input.windowed(windowSize, 1)
        println(input[windowSize + findFirstError(windows)])
    }

    fun run2() {
        val input = processInstructions()
        //window size = i
        for (i in 2 until input.size) {
            input.windowed(i, 1).forEach { window ->
                if (window.sum() == error)
                    println(window.minOrNull()!! + window.maxOrNull()!!)
            }
        }
    }

    fun findFirstError(windows: List<List<Long>>): Int {
        val input = processInstructions()
        val sums = hashMapOf<Int, Set<Long>>()
        for (i in 0..windows.size) {
            sums[i] = prepareSets(windows[i])
            if (!sums[i]!!.contains(input[i + windowSize]))
                return i
        }
        return 0
    }

    fun prepareSets(preamble: List<Long>): Set<Long> =
        mutableSetOf<Long>().also { sums ->
            preamble.forEachIndexed { i, num1 ->
                preamble.forEachIndexed { j, num2 ->
                    if (i != j && !sums.contains(num1 + num2))
                        sums.add(num1 + num2)
                }
            }
        }

    private fun processInstructions() =
        Paths.get(javaClass.classLoader.getResource("day9input.txt")!!.toURI()).toFile()
            .useLines { it.toList().map { it.toLong() } }
}
