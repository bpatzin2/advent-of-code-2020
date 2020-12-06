package days

import org.junit.Test
import kotlin.test.assertEquals

class Day4Test {
  @Test
  fun day4p1TestInput_works() {
    assertEquals(2, day4pt1("input/day4Test.txt"))
  }

  @Test
  fun day4p1_works() {
    assertEquals(254, day4pt1())
  }

  @Test
  fun day4p2TestInput_works() {
    assertEquals(2, day4pt2("input/day4Test.txt"))
  }

  @Test
  fun day4p2_works() {
    assertEquals(184, day4pt2())
  }
}