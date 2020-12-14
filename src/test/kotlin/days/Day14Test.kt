package days

import org.junit.Test
import kotlin.test.assertEquals

class Day14Test {
  @Test
  fun day14p1TestInput_works() {
    assertEquals(165, day14pt1("input/day14Test.txt"))
  }

  @Test
  fun day14p1_works() {
    assertEquals(10035335144067, day14pt1())
  }

  @Test
  fun day14p2TestInput_works() {
    assertEquals(208, day14pt2("input/day14Test2.txt"))
  }

  @Test
  fun day14p2_works() {
    assertEquals(3817372618036, day14pt2())
  }
}