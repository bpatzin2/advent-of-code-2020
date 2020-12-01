package fileinput

import org.junit.Test
import kotlin.test.assertEquals

class FileInputHandlerTest {
  @Test
  fun asDigitList_works() {
    assertEquals(listOf(4,3,2,1), asDigitList("input/input2017Day1Test.txt"))
  }

  @Test
  fun asNumberListByNewLine_works() {
    assertEquals(
      listOf(1721, 979, 366, 299, 675, 1456),
      asNumberListByNewLine("input/day1Test.txt"))
  }
}