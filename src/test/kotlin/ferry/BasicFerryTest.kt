package ferry

import org.junit.Test
import kotlin.test.assertEquals

class BasicFerryTest {
  @Test
  fun applyInstruction_singleMove() {
    val instructions = listOf(
      Instruction('N', 3),
    )

    assertEquals(
      BasicFerry(3, 0, 0),
      BasicFerry(0, 0, 0).applyInstructions(instructions)
    )
  }

  @Test
  fun applyInstruction_singleTurn() {
    val instructions = listOf(
      Instruction('L', 90),
    )

    assertEquals(
      BasicFerry(0, 0, 270),
      BasicFerry(0, 0, 0).applyInstructions(instructions)
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

    val instructions = parseInstructions(testInput.split("\n"))

    assertEquals(
      BasicFerry(-8, 17, 180),
      BasicFerry(0, 0, 90).applyInstructions(instructions)
    )
  }

  @Test
  fun test_distanceFrom() {
    val initialFerry = BasicFerry(0, 0, 90)
    val finalFerry = BasicFerry(-8, 17, 180)
    assertEquals(25, initialFerry.distanceFrom(finalFerry))
  }
}