package computer

import org.junit.Test
import kotlin.test.assertEquals

class ComputerTest {
  private fun assertState(
    acc: Int, linesProcessed: List<Int>, nextLine: Int,
    actualState: ExecutionState
  ){
    assertEquals(acc, actualState.acc)
    assertEquals(linesProcessed, actualState.linesProcessed)
    assertEquals(nextLine, actualState.lineToProcess)
  }

  @Test
  fun jmp0_halts() {
    assertState(0, listOf(1), 1,
      exe(createProgram(listOf(
        JmpInstruction( 0)
      ))))
  }

  @Test
  fun accThenJumpNeg1_halts() {
    val program = createProgram(
      listOf(
        AccInstruction(2),
        JmpInstruction(-1)))

    assertState(2, listOf(1, 2), 1, exe(program))
  }

  private val testInput = """
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
      exe(parse(testInput.split("\n"))))
  }
}