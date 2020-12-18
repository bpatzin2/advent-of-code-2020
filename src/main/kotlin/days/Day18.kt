package days

import mathhomework.*
import java.io.File

fun day18pt1(pathname: String): Long {
  val expressions = File(pathname).readLines()
  return evalAndSum(expressions)
}

fun day18pt1(): Long {
  return day18pt1("input/day18.txt")
}

fun day18pt2(pathname: String): Long {
  val expressions = File(pathname).readLines()
  return evalWithAdditionPrecedenceAndSum(expressions)
}

fun day18pt2(): Long {
  return day18pt2("input/day18.txt")
}

fun main() {
  println("Part 1: " + day18pt1())
  println("Part 2: " + day18pt2())
}