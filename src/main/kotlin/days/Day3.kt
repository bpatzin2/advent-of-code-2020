package days

import fileinput.linesAsCharList
import forest.countTreesOnPath

fun day3pt1(pathname: String): Int {
  val fileInput = linesAsCharList(pathname)
  val givenRight = 3
  val givenDown = 1
  return countTreesOnPath(
    fileInput, givenRight, givenDown)
}

fun day3pt1(): Int {
  return day3pt1("input/day3.txt")
}

fun day3pt2(pathname: String): Int {
  val givenRightDownSlopes = listOf(
    listOf(1, 1),
    listOf(3, 1),
    listOf(5, 1),
    listOf(7, 1),
    listOf(1, 2),
  )
  val fileInput = linesAsCharList(pathname)
  val treesPerSlope = givenRightDownSlopes.map{slope ->
    countTreesOnPath(fileInput, slope[0], slope[1])
  }
  return treesPerSlope.reduce(Int::times)
}

fun day3pt2(): Int {
  return day3pt2("input/day3.txt")
}