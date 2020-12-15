package days

import com.google.common.collect.ArrayListMultimap
import java.io.File

fun playMemoryGame(startingNumbers: List<Int>, turns: Long): Long {
  val spokenNumberIndices: ArrayListMultimap<Long, Long> = initializeGame(startingNumbers)
  val lastStartingNumber = startingNumbers.last().toLong()

  // 1-based indexing
  val remainingTurns = (startingNumbers.size.toLong() + 1)..turns

  return remainingTurns.fold(lastStartingNumber){recentlySpoken, turnNumber ->
    val num = playTurn(recentlySpoken, spokenNumberIndices)
    spokenNumberIndices.put(num, turnNumber)
    num
  }
}

private fun initializeGame(startingNumbers: List<Int>): ArrayListMultimap<Long, Long> {
  val spokenNumberIndices: ArrayListMultimap<Long, Long> = ArrayListMultimap.create()
  startingNumbers.forEachIndexed { idx, num ->
    spokenNumberIndices.put(num.toLong(), idx.toLong() + 1)
  }
  return spokenNumberIndices
}

private fun playTurn(
  prevNumber: Long,
  spokenNumberIndices: ArrayListMultimap<Long, Long>
): Long {
  val indices: List<Long> = spokenNumberIndices.get(prevNumber)
  val size = indices.size
  return if (size > 1) indices[size - 1] - indices[size - 2] else 0
}

fun day15pt1(pathname: String): Long {
  val intList = toIntList(pathname)
  return playMemoryGame(intList, 2020)
}

fun day15pt1(): Long {
  return day15pt1("input/day15.txt")
}

fun day15pt2(pathname: String): Long {
  val intList = toIntList(pathname)
  return playMemoryGame(intList, 30000000)
}

fun day15pt2(): Long {
  return day15pt2("input/day15.txt")
}

private fun toIntList(pathname: String): List<Int> {
  val strList = File(pathname).readLines()
  return strList.first().split(",").map { it.toInt() }
}

fun main() {
  println("Part 1: " + day15pt1())
  println("Part 2: " + day15pt2())
}