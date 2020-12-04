package passport

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestPassport {

  @Test
  fun isValid_falseForEmptyMap() {
    val emptyPassport = mapOf<String, String>()
    assertEquals(false,
      isValid(emptyPassport))
  }

  @Test
  fun isValid_falseForMissingOneRequiredField() {
    val passport = mapOf(
      "byr" to "a",
      "iyr" to "a",
      "eyr" to "a",
      "hgt" to "a",
      "hcl" to "a",
      "ecl" to "a",
    )
    assertEquals(false,
      isValid(passport))
  }

  @Test
  fun isValid_trueForAllRequiredField() {
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
      isValid(passport))
  }

  @Test
  fun fromStringBathes_works() {
    val passportBatch = listOf(
      "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
      "byr:1937 iyr:2017 cid:147 hgt:183cm",
      "",
      "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
      "hcl:#cfa07d byr:1929",
    )
    val actualPassports = fromStringBatches(passportBatch)
    assertTrue(actualPassports[0].containsKey("ecl"))
    assertTrue(actualPassports[1].containsKey("ecl"))
  }

}