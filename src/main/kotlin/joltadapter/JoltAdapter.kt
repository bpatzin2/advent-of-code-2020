package joltadapter

fun joltDifferences(ints: List<Int>): Map<Int, Int>{
  val sorted = ints.sorted()
  val differences: MutableMap<Int, Int> = mutableMapOf();
  sorted.forEachIndexed { index, element ->
    val nextIndex = index + 1
    if(nextIndex < sorted.size) {
      val next = sorted[nextIndex]
      val diff = next - element
      differences[diff] = differences.getOrDefault(diff, 0) + 1
    }
  }
  // handle the difference to the outlet
  differences[sorted.first()] = differences[sorted.first()]!! + 1

  // handle the difference to the device
  differences[3] = differences[3]!! + 1

  return differences
}

fun countArrangements(ints: List<Int>): Long {
  val sorted = ints.sorted()
  return arrangementsRecurCount(0, 0, sorted, mutableMapOf())
}

private fun arrangementsRecurCount(
  windowStart: Int,
  prev: Int,
  all: List<Int>,
  memo: MutableMap<Int, Long>
): Long{
  require(windowStart < all.size)
  var result = 0L
  for(skipCount in 0 until 3){
    val chosenIndex = windowStart + skipCount
    if(chosenIndex >= all.size) continue
    val choice = all[chosenIndex]
    if(choice - prev > 3) continue

    if(choice == all.last()){
      result += 1
      continue
    }

    val subArrangements = memo.getOrPut(
      chosenIndex,
      { arrangementsRecurCount(chosenIndex + 1, choice, all, memo) })

    result += subArrangements
  }
  return result
}