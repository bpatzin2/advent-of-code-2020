package crabcups

import datastructure.LoopedLinkedHashmap
import datastructure.buildLoopedLinkedHashmap
import datastructure.nodeList

data class Game(val cups: LoopedLinkedHashmap){
  fun playRounds(n: Int) {
    for(i in 0 until n){
      playRound()
    }
  }

  fun playRound() {
    val pickupCupsNode = cups.removeNextN(3)
    val pickupCups = nodeList(pickupCupsNode)
    val destinationCup = destinationCupValue(pickupCups)
    val destCupNode = cups.find(destinationCup)
    cups.addAllAfter(destCupNode, pickupCupsNode)
    cups.currNode = cups.currNode.next!!
  }

  fun cupsList(): List<Int>{
    return cups.toList()
  }

  fun currCup(): Int {
    return cups.currValue()
  }

  fun resultString(): String {
    val one = cups.find(1)
    cups.currNode = one
    return cupsList().drop(1).joinToString("")
  }

  private fun destinationCupValue(pickupCups: List<Int>): Int {
    var destinationCup = cups.currNode.int
    do {
      if(destinationCup <= 1){
        destinationCup = cups.max
      }else {
        destinationCup -= 1
      }
    }while (pickupCups.contains(destinationCup))
    return destinationCup
  }
}

fun createGame(cups: List<Int>): Game {
  return Game(buildLoopedLinkedHashmap(cups))
}

fun createMillionGame(cups: List<Int>): Game {
  val milList = buildMillionList(cups)
  return createGame(milList)
}

private fun buildMillionList(intList: List<Int>): List<Int> {
  val max = intList.maxOrNull()!!
  val result = intList.toMutableList()
  result.addAll((max+1)..1_000_000)
  return result
}