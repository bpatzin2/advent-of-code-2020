package days

import org.junit.Test
import kotlin.test.assertEquals

class Day22Test {

  @Test
  fun day22p1TestInput_works() {
    assertEquals(306, day22pt1("input/day22Test.txt"))
  }

  @Test
  fun day22p1_works() {
    assertEquals(34005, day22pt1())
  }

  @Test
  fun day22p2TestInput_works() {
    assertEquals(291L, day22pt2("input/day22Test.txt"))
  }

  @Test
  fun day22p2_works() {
    assertEquals(32731L, day22pt2())
  }
}