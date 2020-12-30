package ferry

import org.junit.Test
import kotlin.test.assertEquals

class FerryWithWaypointTest {
  @Test
  fun applyMove_singleMove() {
    val instructions = listOf(
      Instruction('N', 3),
    )

    val expectedWaypoint =  Waypoint(3, 0)
    val expectedFerry = FerryWithWaypoint(0, 0, expectedWaypoint)

    assertEquals(
      expectedFerry,
      FerryWithWaypoint(0, 0, Waypoint(0, 0))
        .applyInstructions(instructions)
    )
  }

  @Test
  fun applyTurn_L90() {
    val instructions = listOf(
      Instruction('L', 90),
    )

    val initialWaypoint = Waypoint(5, 0)
    val expectedWaypoint =  Waypoint(0, -5)

    assertEquals(
      FerryWithWaypoint(0, 0, expectedWaypoint),
      FerryWithWaypoint(0, 0, initialWaypoint)
        .applyInstructions(instructions)
    )
  }

  @Test
  fun applyTurn_L180() {
    val instructions = listOf(
      Instruction('L', 180),
    )

    val initialWaypoint = Waypoint(5, 0)
    val expectedWaypoint =  Waypoint(-5, 0)

    assertEquals(
      FerryWithWaypoint(0, 0, expectedWaypoint),
      FerryWithWaypoint(0, 0, initialWaypoint)
        .applyInstructions(instructions)
    )
  }

  @Test
  fun part2_testInput() {
    val instructionList = listOf(
      "F10",
      "N3",
      "F7",
      "R90",
      "F11",
    )
    val instructions = parseInstructions(instructionList)

    val initialWaypoint = Waypoint(1, 10)
    val initialFerry = FerryWithWaypoint(0, 0, initialWaypoint)

    assertEquals(
      FerryWithWaypoint(-72, 214, Waypoint(-10, 4)),
      initialFerry.applyInstructions(instructions)
    )
  }
}