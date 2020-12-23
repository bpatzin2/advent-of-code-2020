package days

import fileinput.asString

typealias Card = Int
data class Player(val id: Int, val cards: List<Card>){
  fun getTop(): Pair<Card, Player>{
    val c = cards[0]
    return Pair(c, this.copy(cards = cards.drop(1)))
  }

  fun addToBottom(c1: Card, c2: Card): Player{
    val newCards = cards.toMutableList()
    newCards.add(c1)
    newCards.add(c2)
    return this.copy(cards = newCards)
  }
}

data class RoundResult(val p1: Player, val p2: Player)

fun playRound(p1: Player, p2: Player): RoundResult{
  val (c1, newP1) = p1.getTop()
  val (c2, newP2) = p2.getTop()
  return if(c1 > c2){
    RoundResult(
      newP1.addToBottom(c1, c2),
      newP2,
    )
  }else{
    RoundResult(
      newP1,
      newP2.addToBottom(c2, c1),
    )
  }
}

fun playRecursiveRound(
  p1: Player,
  p2: Player,
  roundCache: MutableMap<Pair<Player, Player>, RoundResult>,
): RoundResult{
  val pair = Pair(p1, p2)
  if(roundCache.containsKey(pair)){
    return roundCache[pair]!!
  }
  val (c1, newP1) = p1.getTop()
  val (c2, newP2) = p2.getTop()
  val result = playRecursiveRound(newP1, c1, newP2, c2, roundCache)
//  roundCache[pair] = result
  return result
}

fun playRecursiveRound(
  p1: Player,
  c1: Card,
  p2: Player,
  c2: Card,
  roundCache: MutableMap<Pair<Player, Player>, RoundResult>,
): RoundResult{
  if(c1 > p1.cards.size || c2 > p2.cards.size){
    return if(c1 > c2){
      RoundResult(
        p1.addToBottom(c1, c2),
        p2,
      )
    }else{
      RoundResult(
        p1,
        p2.addToBottom(c2, c1),
      )
    }
  }else{
    val winner = playRecursiveGame(p1.copy(), p2.copy(), roundCache)
    return if(winner.id == p1.id){
      RoundResult(
        p1.addToBottom(c1, c2),
        p2,
      )
    }else{
      RoundResult(
        p1,
        p2.addToBottom(c2, c1),
      )
    }
  }
}

fun playGame(p1: Player, p2: Player): Player{
  var currP1 = p1
  var currP2 = p2
  while(true){
    val roundResult = playRound(currP1, currP2)
    currP1 = roundResult.p1
    currP2 = roundResult.p2
    if(currP1.cards.isEmpty()){
      return currP2
    }else if(currP2.cards.isEmpty()){
      return currP1
    }
  }
}

fun playRecursiveGame(
  p1: Player,
  p2: Player,
  roundCache: MutableMap<Pair<Player, Player>, RoundResult>,
): Player{
  val result = doPlayRecursiveGame(p1, p2, roundCache)
  return result
}

fun doPlayRecursiveGame(
  p1: Player,
  p2: Player,
  roundCache: MutableMap<Pair<Player, Player>, RoundResult>,
): Player{
  var currP1 = p1
  var currP2 = p2
  val prevStates = mutableSetOf<Pair<Player, Player>>()
  while(true){
    val result = playRecursiveRound(currP1, currP2, roundCache)
    currP1 = result.p1
    currP2 = result.p2
    if(currP1.cards.isEmpty()){
      return currP2
    }else if(currP2.cards.isEmpty()){
      return currP1
    }
    val nextState = Pair(currP1, currP2)
    if(prevStates.contains(nextState)){
      return currP1
    }else{
      prevStates.add(nextState)
    }
  }
}

fun getScore(cards: List<Card>): Long{
  var result = 0L
  cards.forEachIndexed { idx, card ->
    result += (cards.size - idx) * card
  }
  return result
}

fun parse(str: String): Pair<Player, Player> {
  val players = str.split("\n\n")
  val player0 = players[0].split("\n").drop(1)
  val player0Cards = player0.map{s -> s.toInt()}
  val player1 = players[1].split("\n").drop(1)
  val player1Cards = player1.map{s -> s.toInt()}
  return Pair(
    Player(1, player0Cards.toMutableList()),
    Player(2, player1Cards.toMutableList()),
  )
}

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
  val winner = playRecursiveGame(p1, p2, mutableMapOf())
  return getScore(winner.cards)
}

fun day22pt2(): Long {
  return day22pt2("input/day22.txt")
}

fun main() {
//  println("Part 1: " + day22pt1())
  println("Part 2: " + day22pt2())
}