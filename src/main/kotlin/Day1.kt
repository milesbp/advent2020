import java.nio.file.Paths

class Day1 {

    fun run1() {
        println("first")
        val lines = HashMap<Int, Int>()
        val target = 2020
        val resource = javaClass.classLoader.getResource("day1input.txt")!!
        Paths.get(resource.toURI()).toFile().forEachLine {
            if (lines.containsKey(it.toInt()))
                lines[it.toInt()] = 2
            else
                lines[it.toInt()] = 1
        }
        if (lines[target / 2] == 2)
            println("1010, 1010")
        lines.forEach {
            if (lines.containsKey(target - it.key)) {
                println("${it.key}, ${target - it.key}")
                println(it.key.times(target - it.key))
            }
        }
    }

    fun run2() {
        println("second")
        val lines = HashSet<Int>()
        val target = 2020
        val resource = javaClass.classLoader.getResource("day1input.txt")!!
        Paths.get(resource.toURI()).toFile().forEachLine {
            if (!lines.contains(it.toInt()))
                lines.add(it.toInt())
        }
        val hash = hashMapOf<Int, Int>()
        val input = lines.toIntArray()
        for (i in input.indices) {
            for (j in i + 1 until input.size) {
                if (input[i] + input[j] < target && !hash.contains(i + j))
                    hash[input[i] + input[j]] = input[j]
            }
        }
        input.forEach {
            if (hash.contains(target - it)) {
                val j = hash[target - it]!!
                val i = target - it - j
                if (i != j && j != it)
                    println("$it, $j, $i ")
            }
        }
    }

}