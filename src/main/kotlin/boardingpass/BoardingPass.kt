package boardingpass

const val NUM_ROWS = 128
const val NUM_COLS = 8

data class BoardingPass(
  val rowBspInstructions: String,
  val columnBspInstructions: String,
){
  fun seatId() = rowNumber() * 8 + colNumber()
  fun rowNumber() = bsp(0 until NUM_ROWS, rowBspInstructions, 'F')
  fun colNumber() = bsp(0 until NUM_COLS, columnBspInstructions, 'L')
}

fun findSeat(passes: List<BoardingPass>): Int{
  val sortedOccupiedSeats = passes.map{ p -> p.seatId()}.sorted()
  return findGap(sortedOccupiedSeats)
}

fun findGap(intsWithGap: List<Int>): Int {
  val fullRange = intsWithGap.first()..intsWithGap.last()
  return fullRange.first { r -> !intsWithGap.contains(r) }
}

//FBFBBFF RLR
fun parse(rowColBsp: String): BoardingPass{
  val rowBsp = rowColBsp.substring(0, 7)
  val colBsp = rowColBsp.substring(7)
  return BoardingPass(rowBsp, colBsp)
}