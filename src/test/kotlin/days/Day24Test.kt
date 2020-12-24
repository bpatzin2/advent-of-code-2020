package days

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.expect

class Day24Test {

  @Test
  fun moveE() {
    val initGrid = Grid(Coord(0, 0), emptyList())
    val nextLoc = Coord(2, 0)
    val result = initGrid.moveLine(listOf("e"))
    assertEquals(listOf(nextLoc), result.flippedTiles)
  }

  @Test
  fun moveSeveralSteps() {
    val initGrid = Grid(Coord(0, 0), emptyList())
    assertEquals(Coord(-5, -1),
      initGrid.moveLine(listOf("sw", "w", "w"))
        .flippedTiles.last()
    )
  }

  @Test
  fun moveSeveralLines() {
    val initGrid = Grid(Coord(0, 0), emptyList())
    assertEquals(listOf(Coord(-4, 0), Coord(2, 0)),
      initGrid.move(listOf(
        listOf("w", "w"),
        listOf("e")
      )).flippedTiles
    )
  }

  @Test
  fun parseDirs() {
    val input = "swww\nwnene".split("\n")
    assertEquals(
      listOf(listOf("sw", "w", "w"), listOf("w", "ne", "ne")),
      parseDirs(input)
    )
  }

  @Test
  fun blackTiles_works() {
    val initGrid = Grid(Coord(0, 0), emptyList())
    val actual =  initGrid.move(listOf(
      listOf("e"),
      listOf("e", "e", "w"),
      listOf("e", "e"),
    ))
    assertEquals(
      listOf(Coord(2, 0), Coord(2, 0), Coord(4, 0)),
      actual.flippedTiles
    )
    assertEquals(1, actual.countBlackTiles())
  }

  @Test
  fun day24p1TestInput_works() {
    assertEquals(10, day24pt1("input/day24Test.txt"))
  }

  @Test
  fun day24p1_works() {
    assertEquals(538, day24pt1())
  }

  @Test
  fun day24p2OneDay_works() {
    val strs = File("input/day24Test.txt").readLines()
    val dirs = parseDirs(strs)
    val result = Grid(Coord(0,0), emptyList())
      .move(dirs)
      .nextDays(1).size
    assertEquals(15, result)
  }

  @Test
  fun day24p2TestInput_works() {
    assertEquals(2208, day24pt2("input/day24Test.txt"))
  }

  @Test
  fun day24p2_works() {
    assertEquals(4259, day24pt2())
  }
}