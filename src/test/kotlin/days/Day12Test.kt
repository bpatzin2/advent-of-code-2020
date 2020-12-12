package days

import org.junit.Test
import kotlin.test.assertEquals

class Day12Test {

  @Test
  fun day12p1TestInput_works() {
    assertEquals(25, day12pt1("input/day12Test.txt"))
  }

  @Test
  fun day12p1_works() {
    assertEquals(759, day12pt1())
  }

  @Test
  fun day12p2TestInput_works() {
    assertEquals(286, day12pt2("input/day12Test.txt"))
  }

  @Test
  fun day12p2_works() {
    assertEquals(45763, day12pt2())
  }
}