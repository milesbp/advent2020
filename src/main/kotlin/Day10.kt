import data.Node
import java.nio.file.Paths

class Day10 {

    fun run1() {
        val sorted = processAdapters.sorted()
        var ones = 0
        var threes = 1
        for (i in 0..sorted.size - 2) {
            if (sorted[i + 1] - sorted[i] == 1)
                ones++
            else if (sorted[i + 1] - sorted[i] == 3)
                threes++
        }
        if (sorted[0] == 1)
            ones++
        if (sorted[0] == 3)
            threes++
        println(ones * threes)
    }

    fun run2() {
        val sorted = processAdapters.sorted()
        sorted.forEach {
            paths.add(mutableSetOf())
        }
        recurse2(0, mutableSetOf(), sorted.toSet())
        //recurse(sorted, 0, 0)
        setUpTree()
        //println(traverse())
    }

    fun traverse(): Int {
        var count = 0
        for (i in 0..paths.size - 2) {
            paths[i].forEach {
                if (paths[i + 1].contains(it + 1))
                    count++
                if (paths[i + 1].contains(it + 2))
                    count++
                if (paths[i + 1].contains(it + 3))
                    count++
            }
        }
        return count

    }

    var paths = mutableListOf<MutableSet<Int>>()

    fun recurse(list: List<Int>, index: Int, count: Int) {

        when (list.size - index - 1) {
            2 -> {
                val window = list.subList(index, index + 4)
                if (list[index + 2] - list[index] == 3) {
                    paths[count].add(list[index])
                    recurse(list, index + 2, count + 1)
                }
                if (list[index + 1] - list[index] == 3) {
                    paths[count].add(list[index])
                    recurse(list, index + 1, count + 1)
                }
            }
            1 -> {
                val window = list.subList(index, index + 4)
                if (list[index + 1] - list[index] == 1 || list[index + 1] - list[index] == 3) {
                    paths[count].add(list[index])
                    recurse(list, index + 1, count + 1)
                }
            }
            0 -> return
            else -> {
                // val window = list.subList(index, index + 4)
                if (list[index + 3] - list[index] == 3) {
                    paths[count].add(list[index])
                    recurse(list, index + 3, count + 1)
                }
                if (list[index + 2] - list[index] == 3 || list[index + 1] - list[index] == 2) {
                    paths[count].add(list[index])
                    recurse(list, index + 2, count + 1)
                }
                if (list[index + 1] - list[index] == 1 || list[index + 1] - list[index] == 2 || list[index + 1] - list[index] == 3) {
                    paths[count].add(list[index])
                    recurse(list, index + 1, count + 1)
                }
            }
        }
        return
    }


    fun setUpTree() {
        val list = processAdapters.sorted().toHashSet()
        val listMax = list.maxOrNull()
        val windows = list.windowed(4, 1)
        //val root = Node(list[0], mutableSetOf())
        val root = Node(0)
        var nodeList = hashMapOf<Int, Int>().also { map ->
            list.forEach {
                map[it] = 0
            }
        }
        var count = 0



        list.forEach {
            if (list.contains(it + 1))
                nodeList[it + 1] = nodeList[it + 1]!! + 1

            if (list.contains(it + 2))
                nodeList[it + 2] = nodeList[it + 2]!! + 1

            if (list.contains(it + 3))
                nodeList[it + 3] = nodeList[it + 3]!! + 1
        }
        println(count)

        val x = nodeList.map { if (it.value > 1) it.value else 0 }.sum() + 1
        println(x)


    }

    var resultSet: MutableSet<Set<Int>> = mutableSetOf()
    fun recurse2(current: Int, subset: Set<Int>, adapters: Set<Int>) {

        if (current == adapters.maxOfOrNull { it }) {
            val tempSet = subset + current
            resultSet.add(tempSet)
        }

        if (adapters.contains(current + 1)) {
            val tempSet = subset + (current + 1)
            if (!resultSet.contains(tempSet)) {
                resultSet.add(tempSet)
                recurse2(current + 1, tempSet, adapters)
            }
        }

        if (adapters.contains(current + 2)) {
            val tempSet = subset + current + 2
            if (!resultSet.contains(tempSet)) {
                resultSet.add(tempSet)
                recurse2(current + 2, tempSet, adapters)
            }
        }

        if (adapters.contains(current + 3)) {
            val tempSet = subset + (current + 3)
            if (!resultSet.contains(tempSet)) {
                resultSet.add(tempSet)
                recurse2(current + 3, tempSet, adapters)
            }
        }
    }

    /*
       for (i in 1..windows.size) {
            val window = windows[i]
            if (window.contains(curNode.jolt + 1)) {
                curNode.leaves?.let {
                    val node = Node(curNode.jolt + 1)
                    nodeList.add(node)
                    it.add(node)
                } ?: mutableSetOf(Node(curNode.jolt + 1))
            }
            if (window.contains(curNode.jolt + 2)) {
                curNode.leaves?.let {
                    it.add(Node(curNode.jolt + 2))
                } ?: mutableSetOf(Node(curNode.jolt + 2))
            }
            if (window.contains(curNode.jolt + 3)) {
                curNode.leaves?.let {
                    it.add(Node(curNode.jolt + 3))
                } ?: mutableSetOf(Node(curNode.jolt + 3))
            }


        }
     */

    private val processAdapters =
        Paths.get(javaClass.classLoader.getResource("day10input.txt")!!.toURI()).toFile()
            .useLines { it.toList().map { it.toInt() } }
}
