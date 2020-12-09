import data.Instruction
import java.nio.file.Paths

class Day8 {


    fun run1() {
        val instructions = processInstructions()
        val instructionCount = hashMapOf<Int, Int>()
        var accumulator = 0
        var index = 0
        while (instructionCount.count() < instructions.size) {
            val instruction = instructions[index]
            if (!instructionCount.contains(index)) {
                instructionCount[index] = 1
                when (instruction[0]) {
                    "acc" -> {
                        accumulator += instruction[1].toInt()
                        index++
                    }
                    "jmp" -> index += instruction[1].toInt()
                    else -> index++
                }
            } else
                break
        }
        println(accumulator)
    }


    fun run2() {
        val graph = hashMapOf<Int, Instruction>()
        graph.let {
            processInstructions().mapIndexed { index, list ->
                it[index] = Instruction(
                    previous = 0,
                    move = list[0],
                    accumulator = list[1].toInt()
                )
            }
        }
        var swappedIndex: Int = -1
        val totalSwaps = graph.filter { it.value.move != "acc" }.count()
        var swaps = 0

        while (swaps <= totalSwaps) {
            val solution = processInstructions(graph)
            if (solution != null) {
                println(solution)
                break
            }
            swaps++
            if (swappedIndex < 0) {
                graph.filter { it.key > swappedIndex && it.value.move != "acc" }.minOf { it.key }.let {
                    graph[it]!!.move = if (graph[it]!!.move != "nop") "nop" else "jmp"
                    swappedIndex = it
                }
            } else {
                //swap back old swap
                graph[swappedIndex]!!.move = if (graph[swappedIndex]!!.move != "nop") "nop" else "jmp"
                //find new swap farther then previous
                graph.filter { it.key > swappedIndex && it.value.move != "acc" }.minOfOrNull { it.key }?.let {
                    swappedIndex = it
                    //make swap
                    graph[swappedIndex]!!.move = if (graph[swappedIndex]!!.move != "nop") "nop" else "jmp"
                }
            }
        }
    }

    fun processInstructions(graph: HashMap<Int, Instruction>): Int? {
        var total = 0
        var index = 0
        var curCount = 0
        while (curCount < graph.size) {
            if (index == graph.size)
                return total

            val instruction = graph[index]!!
            when (instruction.move) {
                "acc" -> {
                    total += instruction.accumulator
                    index++
                }
                "jmp" -> {
                    index += instruction.accumulator
                }
                else -> index++
            }
            curCount++
        }
        return null
    }

    private fun processInstructions() =
        Paths.get(javaClass.classLoader.getResource("day8input.txt")!!.toURI()).toFile()
            .useLines { it.toList().map { it.split(' ') } }
}
