import java.nio.file.Paths

class Day2 {

    fun run1() {
        val resource = javaClass.classLoader.getResource("day2input.txt")!!
        val input = mutableListOf<Password>()
        Paths.get(resource.toURI()).toFile().forEachLine { it ->
            it.split(' ').let { line ->
                val counts = line[0].split('-')
                input.add(Password(counts[0].toInt(), counts[1].toInt(), line[1].toCharArray()[0], line[2]))
            }
        }
        var validPasswordCount = 0
        input.forEach { passwordData ->
            createHashes(passwordData.password).let {
                if (validateHash(it, passwordData.character, passwordData.min, passwordData.max))
                    validPasswordCount++
            }
        }
        println(validPasswordCount)

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

data class Password(
    val min: Int,
    val max: Int,
    val character: Char,
    val password: String
)