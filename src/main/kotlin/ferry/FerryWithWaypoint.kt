package ferry

data class FerryWithWaypoint(
  override val nsPos: Int,
  override val ewPos: Int,
  val wayPoint: Waypoint): FerryI {

  override fun applyMove(dir: Char, value: Int): FerryWithWaypoint{
    return FerryWithWaypoint(nsPos, ewPos, wayPoint.applyMove(dir, value))
  }

  override fun applyForward(value: Int): FerryWithWaypoint{
    val nsTotalMove = wayPoint.nsDist * value
    val newNs = nsPos + nsTotalMove
    val ewTotalMove = wayPoint.ewDist * value
    val newEw = ewPos + ewTotalMove
    return FerryWithWaypoint(newNs, newEw, wayPoint)
  }

  override fun applyTurn(dir: Char, turnDegrees: Int): FerryWithWaypoint{
    return FerryWithWaypoint(nsPos, ewPos, wayPoint.applyTurn(dir, turnDegrees))
  }
}

data class Waypoint(val nsDist: Int, val ewDist: Int){
  fun applyMove(dir: Char, value: Int): Waypoint{
    return when(dir){
      NORTH -> Waypoint(nsDist + value, ewDist)
      SOUTH -> Waypoint(nsDist - value, ewDist)
      EAST -> Waypoint(nsDist, ewDist + value)
      WEST -> Waypoint(nsDist, ewDist - value)
      else -> throw RuntimeException("applyMove called with $dir")
    }
  }

  fun applyTurn(dir: Char, turnDegrees: Int): Waypoint{
    return if((dir == LEFT && turnDegrees == 90) ||
      (dir == RIGHT && turnDegrees == 270)){
      Waypoint(ewDist, nsDist * -1)
    }else if(turnDegrees == 180){
      Waypoint(nsDist * -1, ewDist * -1)
    }else {
      // RIGHT 90 or LEFT 270
      Waypoint(ewDist * -1, nsDist)
    }
  }
}