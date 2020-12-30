package days

import org.junit.Test
import kotlin.test.assertEquals

class Day10Test {

  @Test
  fun day10p1TestInput_works() {
    assertEquals(220, day10pt1("input/day10Test.txt"))
  }

  @Test
  fun day10p1_works() {
  assertEquals(2380, day10pt1())
  }

  @Test
  fun day10p2TestInput_works() {
    assertEquals(19208, day10pt2("input/day10Test.txt"))
  }

  @Test
  fun day10p2_works() {
    assertEquals(48358655787008, day10pt2())
  }
}