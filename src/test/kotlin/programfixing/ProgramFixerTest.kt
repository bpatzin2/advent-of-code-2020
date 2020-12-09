package programfixing

import computer.*
import org.junit.Test
import kotlin.test.assertEquals

class ProgramFixerTest {

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
  fun test_runFixedProgram() {
    val testProgram = parseInstructions(testInput.split("\n"))
    val result = runFixedProgram(8, toProgram(testProgram))!!
    assertEquals(8, result.acc)
  }

  @Test
  fun test_fixAndRunProgram() {
    val testProgram = parseInstructions(testInput.split("\n"))
    val result = fixAndRunProgram(toProgram(testProgram))!!
    assertEquals(8, result.acc)
  }
}

