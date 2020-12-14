package decoder

import org.junit.Test
import kotlin.test.assertEquals

class BitMaskTest {

  @Test
  fun applyMaskBits_works() {
    val value =  "0101"
    val mask =   "1XX0".toList()
    val result = "1100".toList()
    assertEquals(result, applyMaskToPadded(value, mask))
  }

  @Test
  fun applyMaskLong_works() {
    val value = 5L
    val mask = "1XX0".toList()
    assertEquals(12L, applyMask(value, mask))
  }

  @Test
  fun part1_testStr(){
    val inputLines = """
      mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
      mem[8] = 11
      mem[7] = 101
      mem[8] = 0
    """.trimIndent().split("\n")
    val maskAndInstructions = parseInput(inputLines)
    assertEquals(165, updateMemoryBitMaskedAndSum(maskAndInstructions))
  }
}