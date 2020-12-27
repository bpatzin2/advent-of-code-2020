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
}