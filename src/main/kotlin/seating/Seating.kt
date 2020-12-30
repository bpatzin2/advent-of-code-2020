package seating

import com.google.common.collect.Lists

const val EMPTY_SEAT = 'L'
const val OCCUPIED_SEAT = '#'

interface Seating {
  val grid: List<List<Char>>
  val occupiedSeatThreshold: Int

  fun new(newGrid: List<List<Char>>): Seating
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
    grid.forEach{row -> sb.append(row).append("\n") }
    sb.append("\n")
    return sb.toString()
  }

  private fun runUntilStableState(): Seating{
    var prevState: Seating? = null
    var currState = this
    while(prevState != currState){
      prevState = currState
      currState = currState.updateState()
    }
    return currState
  }

  /*public-for-testing*/
  fun updateState(): Seating{
    val newState =
      grid.indices.map{ row ->
        grid[row].indices.map{col -> updateSeat(row, col)}
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
    val dirs = Lists.cartesianProduct(listOf(-1, 0, 1), listOf(-1, 0, 1))
    return dirs
      .filter {(xDir, yDir) -> !(xDir == 0 && yDir == 0) }
      .mapNotNull{(xDir, yDir) -> getNextSeat(row, col, xDir, yDir)}
  }
}