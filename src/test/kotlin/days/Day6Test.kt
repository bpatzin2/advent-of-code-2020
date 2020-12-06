package days

import org.junit.Test
import kotlin.test.assertEquals

class Day6Test {
  @Test
  fun day6p1TestInput_works() {
    assertEquals(11, day6pt1("input/day6Test.txt"))
  }

  @Test
  fun day6p1_works() {
    assertEquals(6521, day6pt1())
  }

  @Test
  fun day6p2TestInput_works() {
    assertEquals(6, day6pt2("input/day6Test.txt"))
  }

  @Test
  fun day6p2_works() {
    assertEquals(3305, day6pt2())
  }
}