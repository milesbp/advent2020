import data.EyeColors
import data.Passport
import java.nio.file.Paths

class Day4 {

    fun run1() {
        println("Day 4 run 1")
        var total = 0
        val passports = processPassports()
        passports.forEach {
            if (validateValuesExist(it))
                total++
        }
        println(total)
    }

    fun run2() {
        println("Day 4 run 2")
        val passports = processPassports()
        val validPassports = mutableListOf<Passport>()
        passports.forEach {
            if (validateValuesExist(it))
                validateValues(it)?.let { passport ->
                    validPassports.add(passport)
                }
        }
        println(validPassports.size)
    }

    private fun processPassports(): List<HashMap<String, String>> {
        val list = mutableListOf<HashMap<String, String>>()
        var passport = hashMapOf<String, String>()
        Paths.get(javaClass.classLoader.getResource("day4input.txt")!!.toURI()).toFile()
            .forEachLine { line ->
                if (line.isNotEmpty()) {
                    line.split(' ').forEach { kvp ->
                        kvp.split(':').let {
                            passport[it[0]] = it[1]
                        }
                    }
                } else {
                    list.add(passport)
                    passport = hashMapOf()
                }
            }
        list.add(passport)
        return list
    }

    fun validateValues(passport: HashMap<String, String>): Passport? =
        if (
            withinRange(passport["byr"], numberRegex, 1920..2002) &&
            withinRange(passport["iyr"], numberRegex, 2010..2020) &&
            withinRange(passport["eyr"], numberRegex, 2020..2030) &&
            (withinRange(passport["hgt"], heightRegex, 59..76) ||
                    withinRange(passport["hgt"], metricHeightRegex, 150..193)) &&
            hairRegex.matches(passport["hcl"]!!) &&
            eyeColorRegex.matches(passport["ecl"]!!) &&
            passportRegex.matches(passport["pid"]!!)
        )
            Passport(
                byr = passport["byr"]!!.toInt(),
                iyr = passport["iyr"]!!.toInt(),
                eyr = passport["eyr"]!!.toInt(),
                hgt = passport["hgt"]!!,
                hcl = passport["hcl"]!!,
                ecl = EyeColors.valueOf(passport["ecl"]!!),
                pid = passport["pid"]!!.toLong(),
                cid = passport["cid"]
            )
        else
            null

    private fun validateValuesExist(passport: HashMap<String, String>): Boolean =
        passport.contains("ecl") && passport.contains("eyr") && passport.contains("byr") && passport.contains("iyr")
                && passport.contains("hgt") && passport.contains("hcl") && passport.contains("pid")

    private fun withinRange(candidate: String?, regex: Regex, range: IntRange): Boolean =
        candidate?.let {
            regex.matches(it) && numberRegex.find(it)?.groupValues?.get(0)?.toInt() in range
        } ?: false

    private val numberRegex = """\d+""".toRegex()
    private val metricHeightRegex = """[0-9][0-9][0-9]cm""".toRegex()
    private val heightRegex = """[0-9][0-9]in""".toRegex()
    private val hairRegex = """#[0-9a-fA-F]{6}""".toRegex()
    private val passportRegex = """[0-9]{9}""".toRegex()
    private val eyeColorRegex = """(amb|blu|brn|gry|grn|hzl|oth)""".toRegex()

}
