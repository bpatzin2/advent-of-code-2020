package boardingpass

const val NUM_ROWS = 128
const val NUM_COLS = 8

data class BoardingPass(val rowBsp: String, val columnBsp: String){
  fun seatId() = rowNumber() * 8 + colNumber()
  fun rowNumber() = bspRange(0 until NUM_ROWS, rowBsp, 'F')
  fun colNumber() = bspRange(0 until NUM_COLS, columnBsp, 'L')
}

//FBFBBFFRLR
fun fromString(rowColBsp: String): BoardingPass{
  val rowBsp = rowColBsp.substring(0, 7)
  val colBsp = rowColBsp.substring(7)
  return BoardingPass(rowBsp, colBsp)
}

fun findSeat(passes: List<BoardingPass>): Int{
  val sortedOccupiableSeats = passes.map{p -> p.seatId()}.sorted()
  return missingInt(sortedOccupiableSeats)
}

fun missingInt(intsWithGap: List<Int>): Int {
  val intRange = intsWithGap.first()..intsWithGap.last()
  return intRange.first { r -> !intsWithGap.contains(r) }
}