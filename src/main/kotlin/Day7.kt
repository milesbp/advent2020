import data.Bags
import java.nio.file.Paths

class Day7 {

    private val rules = HashMap<String, Set<Bags>>()
    private val memoized = HashMap<String, Boolean>()
    private val SHINY_GOLD = "shiny gold"
    fun run1() {
        processRules().forEach { initRules(it) }
        var count = 0
        rules.forEach {
            if (recurse(it.key)) count++
        }
        println(count)
    }

    fun run2() {}

    fun recurse(color: String): Boolean {

        if (color == SHINY_GOLD)
            return true
        if (rules[color]?.isNotEmpty()!!)
            if (rules[color]?.map { recurse(it.color) }?.contains(true)!!)
                return true
        return false
    }

    private fun initRules(rule: String) {
        val bagContains = hashSetOf<Bags>()
        val split = rule.split("contain", ",", " ")
        var i = 5
        //val bagCount = if (!rule.contains(" no ")) 1 else 0
        val outerBag = split[0] + " " + split[1]
        while (i < split.size - 1 && !rule.contains(" no ")) {
            bagContains.add(
                Bags(
                    color = split[i + 1] + " " + split[i + 2],
                    count = split[i].toInt()
                )
            )
            i += 5
        }
        rules[outerBag] = bagContains
    }

    private fun processRules() =
        Paths.get(javaClass.classLoader.getResource("day7input.txt")!!.toURI()).toFile().useLines { it.toList() }

}
