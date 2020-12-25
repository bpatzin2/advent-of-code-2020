package days

import crabcombat.getScore
import crabcombat.parse
import crabcombat.playGame
import crabcombatrecursive.playRecursiveGame
import fileinput.asString

fun day22pt1(pathname: String): Long {
  val (p1, p2) = parse(asString(pathname))
  val winner = playGame(p1, p2)
  return getScore(winner.cards)
}

fun day22pt1(): Long {
  return day22pt1("input/day22.txt")
}

fun day22pt2(pathname: String): Long {
  val (p1, p2) = parse(asString(pathname))
  val winner = playRecursiveGame(p1, p2)
  return getScore(winner.cards)
}

fun day22pt2(): Long {
  return day22pt2("input/day22.txt")
}

fun main() {
  println("Part 1: " + day22pt1())
  println("Part 2: " + day22pt2())
}