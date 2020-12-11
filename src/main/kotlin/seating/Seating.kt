package seating

const val EMPTY_SEAT = 'L'
const val OCCUPIED_SEAT = '#'

interface SeatingI {
  val grid: List<List<Char>>
  val occupiedSeatThreshold: Int

  fun new(newGrid: List<List<Char>>): SeatingI
  fun getNextSeat(row: Int, col: Int, xDir: Int, yDir: Int): Char?

  fun numOccupiedSeatsAtStableState(): Int{
    val stableState = runUntilStableState()
    val allSeats = stableState.grid.flatten()
    return allSeats.count { it == OCCUPIED_SEAT }
  }

  fun getSquare(row: Int, col: Int): Char?{
    val rowL = grid.getOrNull(row) ?: return null
    return rowL.getOrNull(col)
  }

  fun printGrid(): String {
    val sb = StringBuilder()
    for(row in grid){
      sb.append(row)
      sb.append("\n")
    }
    sb.append("\n")
    return sb.toString()
  }

  private fun runUntilStableState(): SeatingI{
    var prevState: SeatingI? = null
    var currState = this
    while(prevState != currState){
      prevState = currState
      currState = currState.updateState()
    }
    return currState
  }

  private fun updateState(): SeatingI{
    val newState = mutableListOf<List<Char>>()
    for(row in grid.indices){
      val newRow = mutableListOf<Char>()
      for(col in grid[row].indices){
        newRow.add(updateSeat(row, col))
      }
      newState.add(newRow)
    }
    return new(newState)
  }

  private fun updateSeat(row: Int, col: Int): Char {
    val seat = getSquare(row, col) ?: throw RuntimeException("No seat at $row $col")
    if(seat == EMPTY_SEAT){
      val count = getAdjOccupiedCount(row, col)
      return if(count == 0) OCCUPIED_SEAT else EMPTY_SEAT
    }
    if(seat == OCCUPIED_SEAT){
      val count = getAdjOccupiedCount(row, col)
      return if(count >= occupiedSeatThreshold) EMPTY_SEAT else OCCUPIED_SEAT
    }
    return seat
  }

  fun getAdjOccupiedCount(row: Int, col: Int): Int {
    val adjSeats = getAdjacentSeats(row, col)
    return adjSeats.count{it == OCCUPIED_SEAT}
  }

  private fun getAdjacentSeats(row: Int, col: Int): List<Char> {
    val adjSeats = mutableListOf<Char>()
    for (yDir in -1..1) {
      for (xDir in -1..1) {
        if (xDir == 0 && yDir == 0) {
          continue
        }
        val nextSeat = getNextSeat(row, col, xDir, yDir)
        if(nextSeat != null){
          adjSeats.add(nextSeat)
        }
      }
    }
    return adjSeats
  }
}

data class Seating(override val grid: List<List<Char>>) : SeatingI {

  override val occupiedSeatThreshold = 4

  override fun new(newGrid: List<List<Char>>): Seating {
    return Seating(newGrid)
  }

  override fun getNextSeat(row: Int, col: Int, xDir: Int, yDir: Int): Char?{
    val square = getSquare(row + yDir, col + xDir)
    return if(square == OCCUPIED_SEAT || square == EMPTY_SEAT) square else null
  }

  override fun toString(): String {
    return super.printGrid()
  }
}

data class VisibleSeating(override val grid: List<List<Char>>) : SeatingI {

  override val occupiedSeatThreshold = 5

  override fun new(newGrid: List<List<Char>>): VisibleSeating {
    return VisibleSeating(newGrid)
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