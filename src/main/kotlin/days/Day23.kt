package days

import java.io.File
import java.util.*

data class Game(val cups: List<Int>){

  fun playRounds(n: Int): Game {
    var currGame = this
    for(i in 0 until n){
      currGame = currGame.playRound()
      if(i % 1_000 == 0){
        println("round: $i")
      }
    }
    return currGame
  }

  fun playRound(): Game{
    val currentCup = cups[0]
    val pickupCups = cups.subList(1, 4)
    val remainingCups = cups.toMutableList()
    remainingCups.removeAll(pickupCups)

    val destinationCup = destinationCup(currentCup, pickupCups)
    val destCupIdx = remainingCups.indexOf(destinationCup)
    remainingCups.addAll(destCupIdx + 1 , pickupCups)

    val nextCurrentIdx = remainingCups.indexOf(currentCup) + 1 % cups.size
    val result = rotateToStart(nextCurrentIdx, remainingCups)

    return Game(result)
  }

  fun resultString(): String {
    val oneIdx = cups.indexOf(1)
    val result = rotateToStart(oneIdx, cups)
    return result.drop(1).joinToString("")
  }

  private fun destinationCup(currentCup: Int, pickupCups: List<Int>): Int {
    var destinationCup = currentCup
    do {
      if(destinationCup <= 1){
        destinationCup = cups.maxOrNull()!!
      }else {
        destinationCup -= 1
      }
    }while (pickupCups.contains(destinationCup))
    return destinationCup
  }
}

fun rotateToStart(prevIdx: Int, collection: List<Int>):List<Int> {
  val result = collection.toMutableList()
  Collections.rotate(result, result.size - prevIdx)
  return result
}

fun buildMillionList(intList: List<Int>): List<Int> {
  val max = intList.maxOrNull()!!
  val result = intList.toMutableList()
  result.addAll((max+1)..1_000_000)
  return result
}


fun parseToIntList(pathname: String): List<Int>{
  val str = File(pathname).readLines()[0]
  return str.toList().map{c -> c.toString().toInt()}
}

fun day23pt1(pathname: String): String {
  val intList = parseToIntList(pathname)
  val gameResult = Game(intList).playRounds(100)
  return gameResult.resultString()
}

fun day23pt1(): String {
  return day23pt1("input/day23.txt")
}

fun day23pt2(pathname: String): Long {
  val intList = parseToIntList(pathname)
  val milList = buildMillionList(intList)
  val gameResult = Game(milList).playRounds(10_000_000)
  val oneIdx = gameResult.cups.indexOf(1)
  val result = rotateToStart(oneIdx, gameResult.cups)
  return result[0] * result[1].toLong()
}

fun day23pt2(): Long {
  return day23pt2("input/day23.txt")
}

fun main() {
  println("Part 1: " + day23pt1())
//  println("Part 2: " + day23pt2())
}