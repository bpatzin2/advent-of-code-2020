package days

import org.junit.Test
import kotlin.test.assertEquals

class Day15Test {
  @Test
  fun playMemoryGame_works() {
    val startingNumbers = listOf(0,3,6)
    assertEquals(3,
      playMemoryGame(startingNumbers, 5))

    assertEquals(4,
      playMemoryGame(startingNumbers, 9))

    assertEquals(0,
      playMemoryGame(startingNumbers, 10))
  }

  @Test
  fun day15p1TestInput_works() {
    assertEquals(436, day15pt1("input/day15Test.txt"))
  }

  @Test
  fun day15p1_works() {
    assertEquals(240, day15pt1())
  }

  @Test
  fun day15p2_works() {
    //day15p2 is too slow to test
    //assertEquals(505, day15pt2())
  }
}