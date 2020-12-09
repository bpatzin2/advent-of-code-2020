package days

import computer.exe
import computer.parseInstructions
import computer.toProgram
import programfixing.fixAndRunProgram
import java.io.File

fun day8pt1(pathname: String): Int {
  val strList = File(pathname).readLines()
  val instructions = parseInstructions(strList)
  val resultState = exe(instructions)
  return resultState.acc
}

fun day8pt1(): Int {
  return day8pt1("input/day8.txt")
}

fun day8pt2(pathname: String): Int {
  val strList = File(pathname).readLines()
  val instructions = parseInstructions(strList)
  val resultState = fixAndRunProgram(toProgram(instructions))!!
  return resultState.acc
}

fun day8pt2(): Int {
  return day8pt2("input/day8.txt")
}

fun main() {
  println("Part 1: " + day8pt1())
  println("Part 2: " + day8pt2())
}