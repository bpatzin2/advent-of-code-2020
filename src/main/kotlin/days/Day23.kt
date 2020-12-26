package days

import java.io.File

data class Node(val int: Int){
  var prev: Node? = null
  var next: Node? = null
}

fun buildMyLL(list: List<Int>, index: Int = 0): MyLinkedList{
  val result = mutableListOf<Node>()
  var prev: Node? = null
  for(i in list){
    val node = Node(i)
    node.prev = prev
    if(prev != null) prev.next = node
    result.add(node)
    prev = node
  }
  val first = result[0]
  val last = result.last()
  first.prev = last
  last.next = first

  return MyLinkedList(result.toSet(), result[index])
}

data class MyLinkedList(private val llist: Set<Node>, var currNode: Node){
  val max = llist.size
  private val lookup: Map<Int, Node> = llist.map{node -> node.int to node}.toMap()

  fun find(cupInt: Int): Node{
    return lookup[cupInt]!!
  }

  fun move(): List<Long> {
    val p1 = System.nanoTime()
    val pickupCupsNode = removeNextThree()
    val p2 = System.nanoTime()

    val pickupCups = mutableListOf<Int>()
    var node = pickupCupsNode
    while(true){
      pickupCups.add(node.int)
      if(node.next != null) {
        node = node.next!!
      }else{
        break
      }
    }
    val destinationCup = destinationCup(pickupCups)
    val p3 = System.nanoTime()
    val destCupNode = find(destinationCup)
    val p4 = System.nanoTime()

    addAllAfter(destCupNode, pickupCupsNode)
    val p5 = System.nanoTime()
    currNode = currNode.next!!
    return listOf(p1, p2, p3, p4, p5)
  }

  private fun addAllAfter(destCupNode: Node, pickupCups: Node) {
    val right = destCupNode.next!!
    destCupNode.next = pickupCups
    pickupCups.prev = destCupNode

    var end = pickupCups
    while(end.next != null){
      end = end.next!!
    }

    end.next = right
    right.prev = end
  }

  private fun removeNextThree(): Node{
    val first = currNode.next!!
    val second = first.next!!
    val third = second.next!!
    val fourth = third.next!!
    currNode.next = fourth
    fourth.prev = currNode

    third.next = null
    return first
  }

  private fun destinationCup(pickupCups: List<Int>): Int {
    var destinationCup = currNode.int
    do {
      if(destinationCup <= 1){
        destinationCup = max
      }else {
        destinationCup -= 1
      }
    }while (pickupCups.contains(destinationCup))
    return destinationCup
  }
}

fun createGame(cups: List<Int>, currentIndex: Int = 0): Game {
  return Game(buildMyLL(cups), currentIndex)
}

data class Game(val cups: MyLinkedList, var currentIndex: Int){
  fun playRounds(n: Int) {
    for(i in 0 until n){
      playRound()
    }
  }

  fun playRound(): List<Long> {
    return cups.move()
  }

  fun cupsList(): List<Int>{
    val result = mutableListOf<Int>()
    var node = cups.currNode
    for(i in 0 until cups.max){
      result.add(node.int)
      node = node.next!!
    }
    return result
  }

  fun currCup(): Int {
    return cups.currNode.int
  }

  fun resultString(): String {
    val one = cups.find(1)
    cups.currNode = one
    return cupsList().drop(1).joinToString("")
  }
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
  val game = createGame(intList)
  game.playRounds(100)
  return game.resultString()
}

fun day23pt1(): String {
  return day23pt1("input/day23.txt")
}

fun day23pt2(pathname: String): Long {
  val intList = parseToIntList(pathname)
  val milList = buildMillionList(intList)
  val game = createGame(milList)
  game.playRounds(10_000_000)
  val oneNode = game.cups.find(1)
  val next1 = oneNode.next!!
  val next2 = next1.next!!
  return next1.int * next2.int.toLong()
}

fun day23pt2(): Long {
  return day23pt2("input/day23.txt")
}

fun main() {
  println("Part 1: " + day23pt1())
  println("Part 2: " + day23pt2())
}