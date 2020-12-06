import java.nio.file.Paths

class Day6 {

    fun run1() {
        println("Day 6 run 1")
        val lines = processInput()
        var count = 0
        var currentInput = ""
        lines.forEach {
            if (it.isNotEmpty())
                currentInput += it
            else {
                count += currentInput.toHashSet().count()
                currentInput = ""
            }
        }
        count += currentInput.toHashSet().count()
        println(count)
    }

    fun run2() {
        println("Day 6 run 2")
        val lines = processInput()
        var count = 0
        var currentInput = initAlphaSet()

        lines.forEach { survey ->
            if (survey.isNotEmpty())
                currentInput = currentInput.intersect(survey.toHashSet().map { it })
            else {
                count += currentInput.count()
                currentInput = initAlphaSet()
            }
        }
        count += currentInput.count()
        println(count)
    }

    fun processInput() =
        Paths.get(javaClass.classLoader.getResource("day6input.txt")!!.toURI()).toFile().useLines { it.toList() }

    fun initAlphaSet() =
        setOf(
            'a',
            'b',
            'c',
            'd',
            'e',
            'f',
            'g',
            'h',
            'i',
            'j',
            'k',
            'l',
            'm',
            'n',
            'o',
            'p',
            'q',
            'r',
            's',
            't',
            'u',
            'v',
            'w',
            'x',
            'y',
            'z'
        )
}
