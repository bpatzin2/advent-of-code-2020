package boardingpass

fun bspRange(range: IntRange, bsp: String, lowMatch: Char): Int {
  var currRange = range
  for (lowOrHigh in bsp) {
    currRange = if (lowOrHigh == lowMatch) {
      firstHalf(currRange)
    } else {
      secondHalf(currRange)
    }
  }
  return midpoint(currRange)
}

private fun midpoint(range: IntRange) = (range.first + range.last) / 2
private fun firstHalf(range: IntRange) = range.first .. (range.last - (range.count() / 2))
private fun secondHalf(range: IntRange) = (range.first + (range.count() / 2)) .. range.last
