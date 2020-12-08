package halting

import org.junit.Test
import kotlin.test.assertEquals

class HaltingTest {

  private fun assertState(
    acc: Int, linesProcessed: List<Int>, nextLine: Int,
    actualState: ExecutionState){

    assertEquals(acc, actualState.acc)
    assertEquals(linesProcessed, actualState.linesProcessed)
    assertEquals(nextLine, actualState.nextLine)
  }

  @Test
  fun jmp0_halts() {
    assertState(0, listOf(1), 1,
      exe(listOf(
        Instruction("jmp", 0)
      )))
  }

  @Test
  fun accThenJumpNeg1_halts() {
    assertState(2, listOf(1, 2), 1,
      exe(listOf(
        Instruction("acc", 2),
        Instruction("jmp", -1))))
  }

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

  val testInput = """
    nop +0
    acc +1
    jmp +4
    acc +3
    jmp -3
    acc -99
    acc +1
    jmp -4
    acc +6
    """.trimIndent()

  @Test
  fun exe_test_input() {
    assertState(
      5, listOf(1, 2, 3, 7, 8, 4, 5), 2,
        exe(parseInstructions(testInput.split("\n"))))
    }

  @Test
  fun test_runFixedProgram() {
    val testProgram = parseInstructions(testInput.split("\n"))
    val result = runFixedProgram(8, toProgram(testProgram))!!
    assertEquals(8, result.acc)
  }

  @Test
  fun test_fixAndRunProgram() {
    val testProgram = parseInstructions(testInput.split("\n"))
    val result = fixAndRunProgram(testProgram)!!
    assertEquals(8, result.acc)
  }
}

