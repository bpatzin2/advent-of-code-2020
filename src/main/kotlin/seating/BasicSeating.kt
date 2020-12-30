package seating

data class BasicSeating(override val grid: List<List<Char>>) : Seating {
  override val occupiedSeatThreshold = 4

  override fun new(newGrid: List<List<Char>>): BasicSeating {
    return BasicSeating(newGrid)
  }

  override fun getNextSeat(row: Int, col: Int, xDir: Int, yDir: Int): Char?{
    val square = getSquare(row + yDir, col + xDir)
    return if(square == OCCUPIED_SEAT || square == EMPTY_SEAT) square else null
  }

  override fun toString(): String {
    return super.printGrid()
  }
}