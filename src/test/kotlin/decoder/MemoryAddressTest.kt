package decoder

import org.junit.Test
import kotlin.test.assertEquals

class MemoryAddressTest {

  @Test
  fun possibleMasks_works() {
    val addr = "101".toList()
    val mask = "X10".toList()
    val expectedResults = setOf(
      "011".toList(),
      "111".toList(),
    )
    assertEquals(expectedResults, applyAddressDecoder(addr, mask))
  }

  @Test
  fun possibleAddresses_works() {
    val addr = 5 // "101"
    val mask = "X10".toList()
    val expectedResults = setOf(
      3L, // "011"
      7L, // "111"
    )
    assertEquals(expectedResults, possibleAddresses(addr, mask))
  }

  @Test
  fun testStr(){
    val inputLines = """
      mask = 000000000000000000000000000000X1001X
      mem[42] = 100
      mask = 00000000000000000000000000000000X0XX
      mem[26] = 1
    """.trimIndent().split("\n")
    val maskAndInstructions = parseInput(inputLines)
    assertEquals(208, updateMemoryDecoderAndSum(maskAndInstructions))
  }
}