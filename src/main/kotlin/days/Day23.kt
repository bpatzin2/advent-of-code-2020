package days

import crabcups.Game
import crabcups.createGame
import crabcups.createMillionGame
import java.io.File

private fun parseToIntList(pathname: String): List<Int>{
  val str = File(pathname).readLines()[0]
  return str.toList().map{c -> c.toString().toInt()}
}

private fun millionGameAnswer(game: Game): Long {
  val oneNode = game.cups.find(1)
  val next1 = oneNode.next!!
  val next2 = next1.next!!
  return next1.int * next2.int.toLong()
}

fun day23pt1(pathname: String): String {
  val intList = parseToIntList(pathname)
  val game = createGame(intList)
  game.playRounds(100)
  return game.resultString()
}

fun day23pt1(): String {
  return day23pt1("input/day23.txt")
}

fun day23pt2(pathname: String): Long {
  val intList = parseToIntList(pathname)
  val game = createMillionGame(intList)
  game.playRounds(10_000_000)
  return millionGameAnswer(game)
}

fun day23pt2(): Long {
  return day23pt2("input/day23.txt")
}

fun main() {
  println("Part 1: " + day23pt1())
  println("Part 2: " + day23pt2())
}