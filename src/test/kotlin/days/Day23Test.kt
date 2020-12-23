package days

import org.junit.Test
import kotlin.test.assertEquals

class Day23Test {
  @Test
  fun oneRound() {
    val cups = listOf(3,8,9,1,2,5,4,6,7)
    val expectedResult = Game(listOf(2, 8, 9, 1, 5, 4, 6, 7, 3))
    assertEquals(expectedResult, Game(cups).playRound())
    assertEquals(expectedResult, Game(cups).playRounds(1))
  }

  @Test
  fun twoRounds() {
    val cups = listOf(3,8,9,1,2,5,4,6,7)
    val expectedResult = Game(listOf(5, 4, 6, 7, 8, 9, 1, 3, 2))
    assertEquals(expectedResult, Game(cups).playRounds(2))
  }

  @Test
  fun sixthRound() {
    val cups = listOf( 1, 3,  6,  7,9,  2,  5,  8,  4)
    val expectedResult = Game(listOf(9, 3,  6,7,  2,  5,  8,  4,  1))
    assertEquals(expectedResult, Game(cups).playRounds(1))
  }

  @Test
  fun tenRounds() {
    val cups = listOf(3,8,9,1,2,5,4,6,7)
    val expectedResult = Game(listOf(8, 3, 7, 4, 1, 9, 2, 6, 5))
    assertEquals(expectedResult, Game(cups).playRounds(10))
  }

  @Test
  fun resultString() {
    val result = listOf(8, 3, 7, 4, 1, 9, 2, 6, 5)
    assertEquals("92658374", Game(result).resultString())
  }

  @Test
  fun day23p1TestInput_works() {
    assertEquals("67384529", day23pt1("input/day23Test.txt"))
  }

  @Test
  fun day23p1_works() {
    assertEquals("95648732", day23pt1())
  }

  @Test
  fun day23p2TestInput_works() {
//    assertEquals(149245887792, day23pt2("input/day23Test.txt"))
  }

  @Test
  fun day23p2_works() {
//    assertEquals(149245887792, day23pt2())
  }
}