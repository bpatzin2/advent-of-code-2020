package days

import fileinput.asString
import trainticket.Rule
import trainticket.multiplyMyDepartureFields
import trainticket.parseInput

fun sumInvalidValues(
  nearbyTickets: Set<List<Int>>,
  rules: Set<Rule>): Long {
   val invalidValues: List<Int> = nearbyTickets.flatten().filter { ticValue ->
     val inRule = rules.any { rule -> rule.isValidValue(ticValue) }
     !inRule
   }
  return invalidValues.fold(0L, Long::plus)
}

fun day16pt1(pathname: String): Long {
  val fileStr = asString(pathname)
  val inputSections = parseInput(fileStr)
  return sumInvalidValues(
    inputSections.nearbyTickets,
    inputSections.rules
  )
}

fun day16pt1(): Long {
  return day16pt1("input/day16.txt")
}

fun day16pt2(pathname: String): Long {
  val fileStr = asString(pathname)
  val inputSections = parseInput(fileStr)
  return multiplyMyDepartureFields(inputSections)
}

fun day16pt2(): Long {
  return day16pt2("input/day16.txt")
}

fun main() {
  println("Part 1: " + day16pt1())
  println("Part 2: " + day16pt2())
}