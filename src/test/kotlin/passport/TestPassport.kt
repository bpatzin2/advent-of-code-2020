package passport

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestPassport {

  @Test
  fun hasAllRequiredFields_falseForEmptyMap() {
    val emptyPassport = mapOf<String, String>()
    assertEquals(false,
      hasAllRequiredFields(emptyPassport))
  }

  @Test
  fun hasAllRequiredFields_falseForMissingOneRequiredField() {
    val passport = mapOf(
      "byr" to "a",
      "iyr" to "a",
      "eyr" to "a",
      "hgt" to "a",
      "hcl" to "a",
      "ecl" to "a",
    )
    assertEquals(false,
      hasAllRequiredFields(passport))
  }

  @Test
  fun hasAllRequiredFields_trueForAllRequiredField() {
    val passport = mapOf(
      "byr" to "a",
      "iyr" to "a",
      "eyr" to "a",
      "hgt" to "a",
      "hcl" to "a",
      "ecl" to "a",
      "pid" to "a",
    )
    assertEquals(true,
      hasAllRequiredFields(passport))
  }

  @Test
  fun parseBatchOfPassports_works() {
    val passportBatch ="""
      ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
      byr:1937 iyr:2017 cid:147 hgt:183cm
      
      iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
      hcl:#cfa07d byr:1929
      """.trimIndent()
    val actualPassports = parseBatchOfPassports(passportBatch)
    assertTrue(actualPassports[0].containsKey("ecl"))
    assertTrue(actualPassports[1].containsKey("ecl"))
  }

  @Test
  fun isValid_falseForEmptyMap() {
    val emptyPassport = mapOf<String, String>()
    assertEquals(false,
      isValid(emptyPassport)
    )
  }

  @Test
  fun isValid_falseTestData() {
    val passportBatch =
      """
        eyr:1972 cid:100
        hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

        iyr:2019
        hcl:#602927 eyr:1967 hgt:170cm
        ecl:grn pid:012533040 byr:1946

        hcl:dab227 iyr:2012
        ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

        hgt:59cm ecl:zzz
        eyr:2038 hcl:74454a iyr:2023
        pid:3556412378 byr:2007
      """.trimIndent()

    val passports = parseBatchOfPassports(passportBatch)
    assertEquals(4, passports.size)
    passports.forEach { p -> assertFalse(isValid(p)) }
  }

  @Test
  fun isValid_trueTestData() {
    val passportBatch =
      """
      pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
      hcl:#623a2f
      
      eyr:2029 ecl:blu cid:129 byr:1989
      iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm
      
      hcl:#888785
      hgt:164cm byr:2001 iyr:2015 cid:88
      pid:545766238 ecl:hzl
      eyr:2022
      
      iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
      """.trimIndent()

    val passports = parseBatchOfPassports(passportBatch)
    assertEquals(4, passports.size)
    passports.forEach { p -> assertTrue(isValid(p)) }
  }

}