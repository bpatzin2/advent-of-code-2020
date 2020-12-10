package days

import fileinput.asNumberListByNewLine

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

fun oneJoltsTimes3Jolts(ints: List<Int>): Int{
  val differences = joltDifferences(ints)
  return differences[3]!! * differences[1]!!
}

fun arrangementsRecurCount(
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

fun countArrangements(ints: List<Int>): Long {
  val sorted = ints.sorted()
  return arrangementsRecurCount(0, 0, sorted, mutableMapOf())
}

fun day10pt1(pathname: String): Int {
  val intList = asNumberListByNewLine(pathname)
  return oneJoltsTimes3Jolts(intList)
}

fun day10pt1(): Int {
  return day10pt1("input/day10.txt")
}

fun day10pt2(pathname: String): Long {
  val intList = asNumberListByNewLine(pathname)
  return countArrangements(intList)
}

fun day10pt2(): Long {
  return day10pt2("input/day10.txt")
}

fun main() {
  println("Part 1: " + day10pt1())
  println("Part 2: " + day10pt2())
}