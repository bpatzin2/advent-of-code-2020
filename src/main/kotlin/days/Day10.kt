package days

import fileinput.asNumberListByNewLine
import joltadapter.countArrangements
import joltadapter.joltDifferences

fun day10pt1(pathname: String): Int {
  val intList = asNumberListByNewLine(pathname)
  val differences = joltDifferences(intList)
  return differences[3]!! * differences[1]!!
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