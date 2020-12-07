package days

import org.junit.Test
import kotlin.test.assertEquals

class Day7Test {

  @Test
  fun day7p1TestInput_works() {
    assertEquals(4, day7pt1("input/day7Test.txt"))
  }

  @Test
  fun day7p1_works() {
    assertEquals(197, day7pt1())
  }

  @Test
  fun day7p2TestInput_works() {
    assertEquals(32, day7pt2("input/day7Test.txt"))
  }

  @Test
  fun day7p2_works() {
    assertEquals(85324, day7pt2())
  }
}