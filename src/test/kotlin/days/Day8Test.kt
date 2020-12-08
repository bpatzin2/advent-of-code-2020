package days

import org.junit.Test
import kotlin.test.assertEquals

class Day8Test {

  @Test
  fun day8p1TestInput_works() {
    assertEquals(5, day8pt1("input/day8Test.txt"))
  }

  @Test
  fun day8p1_works() {
    assertEquals(1487, day8pt1())
  }

  @Test
  fun day8p2TestInput_works() {
    assertEquals(8, day8pt2("input/day8Test.txt"))
  }

  @Test
  fun day8p2_works() {
    assertEquals(1607, day8pt2())
  }
}