package seating

import org.junit.Test
import kotlin.test.assertEquals

class SeatingTest {
  @Test
  fun getAdjOccupiedCount() {
    val grid = listOf(
      "L.".toList(),
      "L#".toList(),
      "#L".toList(),
    )

    assertEquals(1, BasicSeating(grid).getAdjOccupiedCount(0, 0))
    assertEquals(2, BasicSeating(grid).getAdjOccupiedCount(2, 1))
    assertEquals(1, BasicSeating(grid).getAdjOccupiedCount(2, 0))
  }

  @Test
  fun updateState() {
    val grid = listOf(
      "L..".toList(),
      ".L#".toList(),
      ".##".toList(),
      "###".toList(),
    )

    val expectedGrid = listOf(
      "#..".toList(),
      ".L#".toList(),
      ".LL".toList(),
      "#L#".toList(),
    )

    assertEquals(expectedGrid, BasicSeating(grid).updateState().grid)
  }

  val testInput = """
    L.LL.LL.LL
    LLLLLLL.LL
    L.L.L..L..
    LLLL.LL.LL
    L.LL.LL.LL
    L.LLLLL.LL
    ..L.L.....
    LLLLLLLLLL
    L.LLLLLL.L
    L.LLLLL.LL
  """.trimIndent()

  val testInputStrs = testInput
    .split("\n")
    .map{it.toList()}

  @Test
  fun numOccupiedSeatsAtStableState() {
    assertEquals(37,
      BasicSeating(testInputStrs).numOccupiedSeatsAtStableState())
  }

  val testVisibleSeatsStr = """
    .......#.
    ...#.....
    .#.......
    .........
    ..#L....#
    ....#....
    .........
    #........
    ...#.....
  """.trimIndent()

  val testVisibleSeats = testVisibleSeatsStr
    .split("\n")
    .map{it.toList()}

  @Test
  fun visibleSeating_getAdjOccupiedCount() {
    val grid = listOf(
      "L.".toList(),
      ".L".toList(),
      "##".toList(),
    )

    assertEquals('#', LineOfSightSeating(grid).getNextSeat(0, 0, 0, 1))
    assertEquals('L', LineOfSightSeating(grid).getNextSeat(0, 0, 1, 1))

    assertEquals(1, LineOfSightSeating(grid).getAdjOccupiedCount(0, 0))
    assertEquals(2, LineOfSightSeating(grid).getAdjOccupiedCount(1, 1))
  }

  @Test
  fun visibleSeating_numOccupiedSeatsAtStableState() {
    assertEquals(26,
      LineOfSightSeating(testInputStrs).numOccupiedSeatsAtStableState())
  }
}