package days

import crabcups.Game
import crabcups.createGame
import org.junit.Test
import kotlin.test.assertEquals

class Day23Test {

  private fun assertGame(expectedCups: List<Int>, expectedCup: Int, game: Game){
    assertEquals(expectedCups, game.cupsList())
    assertEquals(expectedCup, game.currCup())
  }

  @Test
  fun oneRound() {
    val cups = listOf(3,8,9,1,2,5,4,6,7)
    val expectedResultList = listOf(2, 8, 9, 1, 5, 4, 6, 7, 3)
    val expectedCup = 2

    var game = createGame(cups)
    game.playRound()
    assertGame(expectedResultList, expectedCup, game)

    game = createGame(cups)
    game.playRounds(1)
    assertGame(expectedResultList, expectedCup, game)
  }

  @Test
  fun twoRounds() {
    val game = createGame(listOf(3,8,9,1,2,5,4,6,7))
    game.playRounds(2)
    assertEquals(5, game.currCup())
  }

  @Test
  fun tenRounds() {
    val cups = listOf(3,8,9,1,2,5,4,6,7)
    val game = createGame(cups)
    game.playRounds(10)
    assertEquals(8, game.currCup())
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
    assertEquals(149245887792, day23pt2("input/day23Test.txt"))
  }

  @Test
  fun day23p2_works() {
//    assertEquals(192515314252, day23pt2())
  }
}