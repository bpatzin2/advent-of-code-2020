package days

import org.junit.Test
import kotlin.test.assertEquals

class Day1Test {
  @Test
  fun day1p1TestInput_works() {
    assertEquals(514579, day1pt1("input/day1Test.txt"))
  }

  @Test
  fun day1p1RealInput_works() {
    assertEquals(969024, day1pt1())
  }

  @Test
  fun day1p2TestInput_works() {
    assertEquals(241861950, day1pt2("input/day1Test.txt"))
  }

  @Test
  fun day1p2_works() {
    assertEquals(230057040, day1pt2())
  }
}