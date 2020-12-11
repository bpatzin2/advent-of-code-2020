package days

import org.junit.Test
import kotlin.test.assertEquals

class Day11Test {

  @Test
  fun day11p1TestInput_works() {
    assertEquals(37, day11pt1("input/day11Test.txt"))
  }

  @Test
  fun day11p1_works() {
    assertEquals(2251, day11pt1())
  }

  @Test
  fun day11p2TestInput_works() {
    assertEquals(26, day11pt2("input/day11Test.txt"))
  }

  @Test
  fun day11p2_works() {
    assertEquals(2019, day11pt2())
  }
}