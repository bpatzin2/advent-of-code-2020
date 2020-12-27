package days

import boardingpass.BoardingPass
import boardingpass.findSeat
import boardingpass.parse
import java.io.File

fun day5pt1(pathname: String): Int {
  val strList = File(pathname).readLines()
  val boardingPasses = strList.map(::parse)
  return boardingPasses.maxOf(BoardingPass::seatId)
}

fun day5pt1(): Int {
  return day5pt1("input/day5.txt")
}

fun day5pt2(pathname: String): Int {
  val strList = File(pathname).readLines()
  val boardingPasses = strList.map(::parse)
  return findSeat(boardingPasses)
}

fun day5pt2(): Int {
  return day5pt2("input/day5.txt")
}