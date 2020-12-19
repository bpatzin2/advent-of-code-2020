package days

import fileinput.asString
import messagerules.Rule
import messagerules.matches
import messagerules.parseRules


data class Input(val rules: Map<Long, Rule>, val msgs: List<String>)

fun parseInput(str: String): Input {
  val rulesAndMsgs = str.split("\n\n")
  return Input(
    parseRules(rulesAndMsgs[0].split("\n")),
    rulesAndMsgs[1].split("\n")
  )
}

fun day19pt1(pathname: String): Int {
  val inputStr = asString(pathname)
  val input = parseInput(inputStr)
  return input.msgs.filter{msg -> matches(msg, input.rules)}.size
}

fun day19pt1(): Int {
  return day19pt1("input/day19.txt")
}

fun day19pt2(): Int {
  return day19pt1("input/day19-2.txt")
}

fun main() {
  println("Part 1: " + day19pt1())
  println("Part 2: " + day19pt2())
}