package boardingpass

import java.lang.RuntimeException

// BSP: Binary Search Protocol
// Takes a range to search and list of instructions for which half
// of the range to take on each step of the binary search
fun bsp(range: IntRange, bspInstructions: String, lowInstruction: Char): Int {
  val resultRange = bspInstructions.fold(range){ currRange, instruction ->
    if (instruction == lowInstruction) {
      firstHalf(currRange)
    } else {
      secondHalf(currRange)
    }
  }
  if(resultRange.count() != 1){
    throw RuntimeException("bsp didn't complete, remaining range: $resultRange")
  }
  return resultRange.first
}

private fun firstHalf(range: IntRange) = range.first .. (range.last - (range.count() / 2))
private fun secondHalf(range: IntRange) = (range.first + (range.count() / 2)) .. range.last
