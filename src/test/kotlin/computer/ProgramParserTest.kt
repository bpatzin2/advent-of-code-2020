package computer

import org.junit.Test
import kotlin.test.assertEquals

class ProgramParserTest {
  @Test
  fun parseInstructions_SingleInstruction() {
    assertEquals(listOf(Instruction("acc", 2)),
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
      Instruction("nop", 0),
      Instruction("acc", 1),
      Instruction("jmp", -4)),
      parseInstructions(testInputStr.split("\n")))
  }
}