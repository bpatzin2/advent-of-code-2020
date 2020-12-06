package days

import org.junit.Test
import kotlin.test.assertEquals

class Day2Test {
  @Test
  fun day2p1TestInput_works() {
    assertEquals(2, day2pt1("input/day2Test.txt"))
  }

  @Test
  fun day2p1_works() {
    assertEquals(465, day2pt1())
  }

  @Test
  fun day2p2TestInput_works() {
    assertEquals(1, day2pt2("input/day2Test2.txt"))
  }

  @Test
  fun day2p2_works() {
    assertEquals(294, day2pt2())
  }
}