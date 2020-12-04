package passport

val REQUIRED_FIELDS = listOf(
  "byr",
  "iyr",
  "eyr",
  "hgt",
  "hcl",
  "ecl",
  "pid",
)
fun isValid(map: Map<String, String>): Boolean{
  return map.keys.containsAll(REQUIRED_FIELDS)
}

// ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
// byr:1937 iyr:2017 cid:147 hgt:183cm
// each passport is separated by an empty line
fun fromStringBatches(batch: List<String>): List<Map<String, String>> {
  val passports = mutableListOf<Map<String, String>>()
  var currMap = mutableMapOf<String, String>()
  for(str in batch){
    if(str.isBlank()){
      passports.add(currMap)
      currMap = mutableMapOf<String, String>()
      continue
    }
    val fields = str.split(" ")
    println("FIELDS")
    println(fields)
    for (field in fields) {
      val keyVal = field.split(":")
      currMap[keyVal[0]] = keyVal[1]
    }
  }
  passports.add(currMap)
  return passports
}