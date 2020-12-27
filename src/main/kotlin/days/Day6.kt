package days

import customs.createDeclarationsByAnyYes
import customs.createDeclarationsByEveryYes
import fileinput.asString

fun day6pt1(pathname: String): Int {
  val str = asString(pathname)
  val declarations = createDeclarationsByAnyYes(str)
  return declarations
    .flatMap{d -> d.yesAnswers}
    .count()
}

fun day6pt1(): Int {
  return day6pt1("input/day6.txt")
}

fun day6pt2(pathname: String): Int {
  val str = asString(pathname)
  val declarations = createDeclarationsByEveryYes(str)
  return declarations
    .flatMap{d -> d.yesAnswers}
    .count()
}

fun day6pt2(): Int {
  return day6pt2("input/day6.txt")
}

fun main() {
  println("Part 1: " + day6pt1())
  println("Part 2: " + day6pt2())
}