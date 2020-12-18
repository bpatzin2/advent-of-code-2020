package days

import org.junit.Test
import kotlin.test.assertEquals

class Day17Test {
  @Test
  fun day17p1TestInput_works() {
    assertEquals(112, day17pt1("input/day17Test.txt"))
  }

  @Test
  fun day17p1_works() {
    assertEquals(388, day17pt1())
  }

  @Test
  fun day17p2TestInput_works() {
    assertEquals(848, day17pt2("input/day17Test.txt"))
  }

  @Test
  fun day17p2_works() {
    assertEquals(2280, day17pt2())
  }
}