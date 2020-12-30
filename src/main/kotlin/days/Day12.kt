package days

import ferry.*
import java.io.File

fun day12pt1(pathname: String): Int {
  val strList = File(pathname).readLines()
  val instructions = parseInstructions(strList)
  val initialFerry = BasicFerry(0, 0, 90)
  return initialFerry
    .applyInstructions(instructions)
    .distanceFrom(initialFerry)
}

fun day12pt1(): Int {
  return day12pt1("input/day12.txt")
}

fun day12pt2(pathname: String): Int {
  val strList = File(pathname).readLines()
  val instructions = parseInstructions(strList)
  val initialFerry = createFerryWithWaypoint(1, 10)
  return initialFerry
    .applyInstructions(instructions)
    .distanceFrom(initialFerry)
}

fun day12pt2(): Int {
  return day12pt2("input/day12.txt")
}

fun main() {
  println("Part 1: " + day12pt1())
  println("Part 2: " + day12pt2())
}