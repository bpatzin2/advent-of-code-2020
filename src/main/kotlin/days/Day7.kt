package days

import countNumContained
import fileinput.asString
import findAllBagsThatCanContain
import parseInput

fun day7pt1(pathname: String): Int {
  val inputStr = asString(pathname)
  val input = parseInput(inputStr)
  val result: Set<String> = findAllBagsThatCanContain("shiny gold", input)
  return result.size
}

fun day7pt1(): Int {
  return day7pt1("input/day7.txt")
}

fun day7pt2(pathname: String): Int {
  val inputStr = asString(pathname)
  val input = parseInput(inputStr)
  return countNumContained("shiny gold", input)
}

fun day7pt2(): Int {
  return day7pt2("input/day7.txt")
}

fun main() {
  println("Part 1: " + day7pt1())
  println("Part 2: " + day7pt2())
}