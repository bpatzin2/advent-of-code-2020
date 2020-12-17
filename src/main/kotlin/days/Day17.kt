package days

import conway.nextState
import conway.parse
import java.io.File

fun day17pt1(pathname: String): Int {
  val strList = File(pathname).readLines()
  val initialState = parse(strList, 3)
  var currState = initialState
  for(i in 0 until 6){
    currState = nextState(currState)
  }
  return currState.size
}

fun day17pt1(): Int {
  return day17pt1("input/day17.txt")
}

fun day17pt2(pathname: String): Int {
  val strList = File(pathname).readLines()
  val initialState = parse(strList, 4)
  var currState = initialState
  for(i in 0 until 6){
    currState = nextState(currState)
  }
  return currState.size
}

fun day17pt2(): Int {
  return day17pt2("input/day17.txt")
}

fun main() {
  println("Part 1: " + day17pt1())
  println("Part 2: " + day17pt2())
}