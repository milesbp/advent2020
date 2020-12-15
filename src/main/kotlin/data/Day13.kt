package data

import java.nio.file.Paths

class Day13 {

    fun run1() {
        val time = processBusScheduled[0].toInt()
        val busses = processBusScheduled[1].split(',').filterNot { it == "x" }
        val routes = hashMapOf<Int, Int>()
        busses.forEach { routes[it.toInt()] = it.toInt() - (time % it.toInt()) }
        var min = Pair<Int, Int>(0, Int.MAX_VALUE)
        routes.forEach {
            if (it.value < min.second)
                min = it.toPair()
        }
        println(min.first * min.second)
    }

    private val processBusScheduled =
        Paths.get(javaClass.classLoader.getResource("day13input.txt")!!.toURI()).toFile()
            .useLines { it.toList() }
}
