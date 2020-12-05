package data

class Passport(
    val byr: Int,
    val iyr: Int,
    val eyr: Int,
    val hgt: String,
    val hcl: String,
    var ecl: EyeColors,
    val pid: Long,
    val cid: String?,

    ) {


    private fun withinRange(candidate: String?, regex: Regex, range: IntRange): Boolean =
        candidate?.let {
            regex.matches(it) && numberRegex.find(it)?.groupValues?.get(0)?.toInt() in range
        } ?: false

    private val numberRegex = """\d+""".toRegex()
    private val onlyDigitsRegex = """^[0-9]*""".toRegex()
    private val metricHeightRegex = """[0-9][0-9][0-9]cm""".toRegex()
    private val heightRegex = """[0-9][0-9]in""".toRegex()
    private val hairRegex = """#[0-9a-fA-F]{6}""".toRegex()
    private val passportRegex = """[0-9]{9}"""
}


