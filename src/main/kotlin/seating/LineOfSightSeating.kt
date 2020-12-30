package seating

data class LineOfSightSeating(override val grid: List<List<Char>>) : Seating {

  override val occupiedSeatThreshold = 5

  override fun new(newGrid: List<List<Char>>): LineOfSightSeating {
    return LineOfSightSeating(newGrid)
  }

  override fun getNextSeat(row: Int, col: Int, xDir: Int, yDir: Int): Char? {
    var nextRow = row + yDir
    var nextCol = col + xDir
    var nextSquare = getSquare(nextRow, nextCol)
    while(nextSquare != null){
      if(nextSquare == OCCUPIED_SEAT || nextSquare == EMPTY_SEAT){
        return nextSquare
      }
      nextRow += yDir
      nextCol += xDir
      nextSquare = getSquare(nextRow, nextCol)
    }
    return null
  }

  override fun toString(): String {
    return super.printGrid()
  }
}