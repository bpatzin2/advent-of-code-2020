package crabcombat

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
