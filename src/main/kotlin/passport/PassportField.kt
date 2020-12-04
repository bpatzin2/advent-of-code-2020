package passport

import java.lang.RuntimeException

fun isFieldValid(key: String, value: String): Boolean{
  return when (key) {
    "byr" -> isByrValid(value)
    "iyr" -> isIyrValid(value)
    "eyr" -> isEyrValid(value)
    "hgt" -> isHgtValid(value)
    "hcl" -> isHclValid(value)
    "ecl" -> isEclValid(value)
    "pid" -> isPidValid(value)
    else -> {
      throw RuntimeException("unknown field $key")
    }
  }
}

//byr (Birth Year) - four digits; at least 1920 and at most 2002.
fun isByrValid(value: String): Boolean {
  val year = value.toIntOrNull() ?: return false
  return year in 1920..2002
}

//iyr (Issue Year) - four digits; at least 2010 and at most 2020.
fun isIyrValid(value: String): Boolean {
  val year = value.toIntOrNull() ?: return false
  return year in 2010..2020
}

//eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
fun isEyrValid(value: String): Boolean {
  val year = value.toIntOrNull() ?: return false
  return year in 2020..2030
}

//hgt (Height) - a number followed by either cm or in:
//If cm, the number must be at least 150 and at most 193.
//If in, the number must be at least 59 and at most 76.
fun isHgtValid(value: String): Boolean {
  if(value.endsWith("cm")){
    val cmsStr = value.removeSuffix("cm")
    val cms = cmsStr.toIntOrNull() ?: return false
    return cms in 150..193
  }
  if(value.endsWith("in")){
    val insStr = value.removeSuffix("in")
    val ins = insStr.toIntOrNull() ?: return false
    return ins in 59..76
  }
  return false
}

//hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
fun isHclValid(value: String): Boolean {
  val regex = Regex("^#[a-f0-9]{6}\$")
  return regex.containsMatchIn(value)
}

val VALID_EYES = listOf(
"amb", "blu", "brn", "gry", "grn", "hzl", "oth",
)
//ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
fun isEclValid(value: String): Boolean =
  VALID_EYES.contains(value)

//pid (Passport ID) - a nine-digit number, including leading zeroes.
fun isPidValid(value: String): Boolean =
  value.count() == 9 &&
    value.toIntOrNull() != null
