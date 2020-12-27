package days

import luggage.countBagsContainedIn
import fileinput.asString
import luggage.allBagsThatContain
import luggage.parseBagRules

fun day7pt1(pathname: String): Int {
  val inputStr = asString(pathname)
  val bagRules = parseBagRules(inputStr)
  val result: Set<String> = allBagsThatContain("shiny gold", bagRules)
  return result.size
}

fun day7pt1(): Int {
  return day7pt1("input/day7.txt")
}

fun day7pt2(pathname: String): Int {
  val inputStr = asString(pathname)
  val bagRules = parseBagRules(inputStr)
  return countBagsContainedIn("shiny gold", bagRules)
}

fun day7pt2(): Int {
  return day7pt2("input/day7.txt")
}

fun main() {
  println("Part 1: " + day7pt1())
  println("Part 2: " + day7pt2())
}