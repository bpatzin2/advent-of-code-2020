package ferry

data class BasicFerry(
  override val nsPos: Int,
  override val ewPos: Int,
  val degrees: Int) : Ferry {

  override fun applyMove(dir: Char, value: Int): BasicFerry{
    return when(dir){
      NORTH -> BasicFerry(nsPos + value, ewPos, degrees )
      SOUTH -> BasicFerry(nsPos - value, ewPos, degrees )
      EAST -> BasicFerry(nsPos, ewPos + value, degrees )
      WEST -> BasicFerry(nsPos, ewPos - value, degrees )
      else -> throw RuntimeException("applyMove called with $dir")
    }
  }

  override fun applyForward(value: Int): BasicFerry {
    val actualDir = when (degrees) {
      0 -> NORTH
      90 -> EAST
      180 -> SOUTH
      270 -> WEST
      else -> throw RuntimeException("FORWARD called with $degrees")
    }
    return applyMove(actualDir, value)
  }

  override fun applyTurn(dir: Char, turnDegrees: Int): BasicFerry{
    val newDegrees: Int = when(dir){
      LEFT -> degrees - turnDegrees
      RIGHT -> degrees + turnDegrees
      else -> throw RuntimeException("applyTurn called with $dir")
    }
    return BasicFerry(nsPos, ewPos, normalizeDegrees(newDegrees))
  }

  private fun normalizeDegrees(newDegrees: Int): Int {
    return when {
      newDegrees >= 360 -> newDegrees - 360
      newDegrees < 0 -> newDegrees + 360
      else -> newDegrees
    }
  }
}