package ferry

data class Ferry(
  override val nsPos: Int,
  override val ewPos: Int,
  val degrees: Int) : FerryI {

  override fun applyMove(dir: Char, value: Int): Ferry{
    return when(dir){
      NORTH -> Ferry(nsPos + value, ewPos, degrees )
      SOUTH -> Ferry(nsPos - value, ewPos, degrees )
      EAST -> Ferry(nsPos, ewPos + value, degrees )
      WEST -> Ferry(nsPos, ewPos - value, degrees )
      else -> throw RuntimeException("applyMove called with $dir")
    }
  }

  override fun applyForward(value: Int): Ferry {
    val actualDir = when (degrees) {
      0 -> NORTH
      90 -> EAST
      180 -> SOUTH
      270 -> WEST
      else -> throw RuntimeException("FORWARD called with $degrees")
    }
    return applyMove(actualDir, value)
  }

  override fun applyTurn(dir: Char, turnDegrees: Int): Ferry{
    val newDegrees: Int = when(dir){
      LEFT -> degrees - turnDegrees
      RIGHT -> degrees + turnDegrees
      else -> throw RuntimeException("applyTurn called with $dir")
    }
    return Ferry(nsPos, ewPos, normalizeDegrees(newDegrees))
  }

  private fun normalizeDegrees(newDegrees: Int): Int {
    return when {
      newDegrees >= 360 -> newDegrees - 360
      newDegrees < 0 -> newDegrees + 360
      else -> newDegrees
    }
  }
}