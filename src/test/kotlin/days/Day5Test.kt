package days

import org.junit.Test
import kotlin.test.assertEquals

class Day5Test {
  @Test
  fun day5p1TestInput_works() {
    assertEquals(820, day5pt1("input/day5Test.txt"))
  }

  @Test
  fun day5p1_works() {
    assertEquals(850, day5pt1())
  }

  @Test
  fun day5p2_works() {
    assertEquals(599, day5pt2())
  }
}