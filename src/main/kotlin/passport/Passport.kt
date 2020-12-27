package passport

import groupByEmptyLine

val REQUIRED_FIELDS = listOf(
  "byr",
  "iyr",
  "eyr",
  "hgt",
  "hcl",
  "ecl",
  "pid",
)

fun hasAllRequiredFields(map: Map<String, String>): Boolean{
  return map.keys.containsAll(REQUIRED_FIELDS)
}

fun isValid(map: Map<String, String>): Boolean{
  if(!hasAllRequiredFields(map)){
    return false
  }
  return map.entries
    .filter { field -> REQUIRED_FIELDS.contains(field.key) }
    .all{field -> isFieldValid(field.key, field.value)}
}

// ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
// byr:1937 iyr:2017 cid:147 hgt:183cm
// each passport is separated by an empty line
fun parseBatchOfPassports(batch: String): List<Map<String, String>> {
  val passportsAsLists = groupByEmptyLine(batch)
  return passportsAsLists.map{p -> parsePassport(p)}
}

fun parsePassport(passport: List<String>): Map<String, String> {
  val result = mutableMapOf<String, String>()
  for (line in passport) {
    val fields = line.split(" ")
    for (field in fields) {
      val keyVal = field.split(":")
      result[keyVal[0]] = keyVal[1]
    }
  }
  return result
}