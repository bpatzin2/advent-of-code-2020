package days

import seating.Seating
import seating.VisibleSeating
import java.io.File

private fun toGrid(pathname: String): List<List<Char>> {
  val strList = File(pathname).readLines()
  return strList.map { it.toList() }
}

fun day11pt1(pathname: String): Int {
  val grid = toGrid(pathname)
  return Seating(grid).numOccupiedSeatsAtStableState()
}

fun day11pt1(): Int {
  return day11pt1("input/day11.txt")
}

fun day11pt2(pathname: String): Int {
  val grid = toGrid(pathname)
  return VisibleSeating(grid).numOccupiedSeatsAtStableState()
}

fun day11pt2(): Int {
  return day11pt2("input/day11.txt")
}

fun main() {
  println("Part 1: " + day11pt1())
  println("Part 2: " + day11pt2())
}