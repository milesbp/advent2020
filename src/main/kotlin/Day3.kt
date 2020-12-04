import java.nio.file.Paths

class Day3 {

    fun run1() {
        println("day 3 run 1 ")
        val geology = buildTobogganGeology()
        var treeCount = 0
        geology.forEachIndexed { i, pattern ->
            if (pattern[(i * 3) % pattern.length] == '#')
                treeCount++
        }
        println(treeCount)
    }

    fun run2() {
        println("day 3 run 2 ")
        val geology = buildTobogganGeology()
        println(
            tobogganDown(geology, 1, 1) *
                    tobogganDown(geology, 3, 1) *
                    tobogganDown(geology, 5, 1) *
                    tobogganDown(geology, 7, 1) *
                    tobogganDown(geology, 1, 2)
        )
    }

    fun tobogganDown(geology: List<String>, right: Int, down: Int): Long {
        println("right:$right, down:$down")
        var treeCount = 0L
        for (i in 0 until (geology.size / down)) {
            if (geology[i * down][(i * right) % geology[i * down].length] == '#')
                treeCount++
        }
        println(treeCount)
        return treeCount
    }

    fun buildTobogganGeology(): List<String> =
        mutableListOf<String>().also { list ->
            Paths.get(javaClass.classLoader.getResource("day3input.txt")!!.toURI()).toFile()
                .forEachLine { list.add(it) }
        }

}
