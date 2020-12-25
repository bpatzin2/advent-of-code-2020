package crabcombatrecursive

import crabcombat.Card
import crabcombat.Player

data class RoundResult(
  val p1: Player,
  val p2: Player,
)

fun playRecursiveGame(
  p1: Player,
  p2: Player,
): Player{
  var currP1 = p1
  var currP2 = p2
  val prevStates = mutableSetOf<Pair<Player, Player>>()
  while(true){
    val result = playRecursiveRound(currP1, currP2)
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

fun playRecursiveRound(
  origP1: Player,
  origP2: Player,
): RoundResult{
  val (c1, p1) = origP1.getTop()
  val (c2, p2) = origP2.getTop()
  return if(c1 > p1.cards.size || c2 > p2.cards.size){
    val winner = if(c1 > c2) p1 else p2
    roundResult(winner.id, p1, c1, p2, c2)
  }else{
    val subP1 = p1.copy(cards=p1.cards.take(c1))
    val subP2 = p2.copy(cards=p2.cards.take(c2))
    val winner = playRecursiveGame(subP1, subP2)
    roundResult(winner.id, p1, c1, p2, c2)
  }
}

private fun roundResult(
  winnerId: Int,
  p1: Player,
  c1: Card,
  p2: Player,
  c2: Card
): RoundResult {
  return if (winnerId == p1.id) {
    RoundResult(
      p1.addToBottom(c1, c2),
      p2,
    )
  } else {
    RoundResult(
      p1,
      p2.addToBottom(c2, c1),
    )
  }
}