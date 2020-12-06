package days

import org.junit.Test
import kotlin.test.assertEquals

class Day3Test {
  @Test
  fun day3p1TestInput_works() {
    assertEquals(7, day3pt1("input/day3Test.txt"))
  }

  @Test
  fun day3p1_works() {
    assertEquals(193, day3pt1())
  }

  @Test
  fun day3p2TestInput_works() {
    assertEquals(336, day3pt2("input/day3Test.txt"))
  }

  @Test
  fun day3p2_works() {
    assertEquals(1355323200, day3pt2())
  }
}