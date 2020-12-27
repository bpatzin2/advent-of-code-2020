package days

import org.junit.Test
import passwordpolicy.PasswordPolicy
import passwordpolicy.PasswordWithPolicy
import kotlin.test.assertEquals

class Day2Test {

  @Test
  fun toPasswordsWithPolicies_works() {
    //1-3 a: abcde
    //1-3 b: cdefg
    //2-9 c: ccccccccc
    val expectedPasswordWithPolicies = listOf(
      PasswordWithPolicy("abcde", PasswordPolicy("a", 1, 3)),
      PasswordWithPolicy("cdefg", PasswordPolicy("b", 1, 3)),
      PasswordWithPolicy("ccccccccc", PasswordPolicy("c", 2, 9))
    )
    assertEquals(
      expectedPasswordWithPolicies,
      parsePasswordWithPolicies("input/day2Test.txt")
    )
  }

  @Test
  fun day2p1TestInput_works() {
    assertEquals(2, day2pt1("input/day2Test.txt"))
  }

  @Test
  fun day2p1_works() {
    assertEquals(465, day2pt1())
  }

  @Test
  fun day2p2TestInput_works() {
    assertEquals(1, day2pt2("input/day2Test2.txt"))
  }

  @Test
  fun day2p2_works() {
    assertEquals(294, day2pt2())
  }
}