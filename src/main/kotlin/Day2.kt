import data.Password
import java.nio.file.Paths

class Day2 {

    fun run1() {
        println("run 1")
        var validPasswordCount = 0
        buildPasswordList().forEach { passwordData ->
            createHashes(passwordData.password).let {
                if (validateHash(it, passwordData.character, passwordData.min, passwordData.max))
                    validPasswordCount++
            }
        }
        println(validPasswordCount)
    }

    fun run2() {
        println("run 2")
        var validPassworrdCount = 0
        buildPasswordList().forEach {
            if ((it.password[it.min - 1] == it.character || it.password[it.max - 1] == it.character)
                && it.password[it.min - 1] != it.password[it.max - 1]
            )
                validPassworrdCount++
        }
        println(validPassworrdCount)
    }

    fun buildPasswordList(): MutableList<Password> {
        val resource = javaClass.classLoader.getResource("day2input.txt")!!
        val input = mutableListOf<Password>()
        Paths.get(resource.toURI()).toFile().forEachLine { it ->
            it.split(' ').let { line ->
                val counts = line[0].split('-')
                input.add(Password(counts[0].toInt(), counts[1].toInt(), line[1][0], line[2]))
            }
        }
        return input
    }

    private fun createHashes(password: String): HashMap<Char, Int> {
        val countMap = hashMapOf<Char, Int>()
        password.toCharArray().forEach {
            if (countMap.containsKey(it))
                countMap[it] = countMap[it]!! + 1
            else
                countMap[it] = 1
        }
        return countMap
    }

    private fun validateHash(password: HashMap<Char, Int>, special: Char, min: Int, max: Int): Boolean =
        try {
            password[special]!! in min..max
        } catch (ex: NullPointerException) {
            false
        }

}
