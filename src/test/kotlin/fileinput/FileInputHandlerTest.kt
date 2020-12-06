package fileinput

import org.junit.Test
import passwordpolicy.PasswordPolicy
import passwordpolicy.PasswordWithPolicy
import kotlin.test.assertEquals

class FileInputHandlerTest {
  @Test
  fun asNumberListByNewLine_works() {
    assertEquals(
      listOf(1721, 979, 366, 299, 675, 1456),
      asNumberListByNewLine("input/day1Test.txt"))
  }

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
      toPasswordsWithPolicies("input/day2Test.txt")
    )
  }
}