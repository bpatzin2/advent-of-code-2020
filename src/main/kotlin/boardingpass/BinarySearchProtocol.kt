package boardingpass

import java.lang.RuntimeException

fun bspRange(range: IntRange, bsp: String, lowMatch: Char): Int {
  val resultRange = bsp.fold(range){currRange, lowOrHigh ->
    if (lowOrHigh == lowMatch) {
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
