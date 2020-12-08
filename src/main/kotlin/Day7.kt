import data.Bags
import java.nio.file.Paths

class Day7 {

    private val rules = HashMap<String, Set<Bags>>()
    private val memoized = HashMap<String, Pair<Boolean, Boolean>>()
    private val SHINY_GOLD = "shiny gold"
    fun run1() {
        processRules().forEach { initRules(it) }
        rules.forEach { recurse(it.key) }
        println(memoized.map {
            if (it.value.first && it.value.second && it.key != SHINY_GOLD) 1 else 0
        }.sum())
    }

    fun run2() {
        processRules().forEach { initRules(it) }
        println(recurseSum(SHINY_GOLD, 1) - 1)
    }

    fun recurseSum(color: String, count: Int): Int =
        if (!rules[color].isNullOrEmpty())
            rules[color]?.map { recurseSum(it.color, it.count) }!!.sum() * count + count
        else count


    fun recurse(color: String): Boolean {
        if (color == SHINY_GOLD) {
            memoized[color] = Pair(first = true, second = true)
            return true
        }
        if (rules[color]?.isNotEmpty()!!)
            if (rules[color]?.map { recurse(it.color) }?.contains(true)!!) {
                memoized[color] = Pair(first = true, second = true)
                return true
            }
        memoized[color] = Pair(first = true, second = false)
        return false
    }

    private fun initRules(rule: String) {
        val bagContains = hashSetOf<Bags>()
        val split = rule.split("contain", ",", " ")
        var i = 5
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
