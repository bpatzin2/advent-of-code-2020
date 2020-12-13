package days

import org.junit.Test
import kotlin.test.assertEquals

class Day13Test {

  @Test
  fun day13p1TestInput_works() {
    assertEquals(295, day13pt1("input/day13Test.txt"))
  }

  @Test
  fun day13p1_works() {
    assertEquals(259, day13pt1())
  }

  @Test
  fun day13p2TestInput_works() {
    assertEquals(1068781, day13pt2("input/day13Test.txt"))
  }

  @Test
  fun day13p2_works() {
    assertEquals(210612924879242, day13pt2())
  }
}