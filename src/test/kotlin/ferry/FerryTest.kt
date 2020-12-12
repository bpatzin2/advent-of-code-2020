package ferry

import org.junit.Test
import kotlin.test.assertEquals

class FerryTest {
  @Test
  fun applyInstruction_singleMove() {
    val instructions = listOf(
      Pair('N', 3),
    )

    assertEquals(
      Ferry(3, 0, 0),
      Ferry(0, 0, 0).applyInstructions(instructions)
    )
  }

  @Test
  fun applyInstruction_singleTurn() {
    val instructions = listOf(
      Pair('L', 90),
    )

    assertEquals(
      Ferry(0, 0, 270),
      Ferry(0, 0, 0).applyInstructions(instructions)
    )
  }

  @Test
  fun part1_testInput() {
    val testInput = """
      F10
      N3
      F7
      R90
      F11
    """.trimIndent()

    val instructions = parseInput(testInput.split("\n"))

    assertEquals(
      Ferry(-8, 17, 180),
      Ferry(0, 0, 90).applyInstructions(instructions)
    )
  }

  @Test
  fun test_distanceFrom() {
    val initialFerry = Ferry(0, 0, 90)
    val finalFerry = Ferry(-8, 17, 180)
    assertEquals(25, initialFerry.distanceFrom(finalFerry))
  }
}