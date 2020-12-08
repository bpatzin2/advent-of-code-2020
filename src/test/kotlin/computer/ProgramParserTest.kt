package computer

import org.junit.Test
import kotlin.test.assertEquals

class ProgramParserTest {
  @Test
  fun parseInstructions_SingleInstruction() {
    assertEquals(listOf(AccInstruction(2)),
      parseInstructions(listOf("acc +2")))
  }

  val testInputStr = """
    nop +0
    acc +1
    jmp -4
    """.trimIndent()

  @Test
  fun parseInstructions_multiple() {
    assertEquals(listOf(
      NopInstruction(0),
      AccInstruction(1),
      JmpInstruction(-4)),
      parseInstructions(testInputStr.split("\n")))
  }
}