package boardingpass

import java.lang.RuntimeException

const val NUM_ROWS = 128
const val NUM_COLS = 8

data class BoardingPass(val rowBsp: String, val columnBsp: String){

  fun seatId(): Int{
    return rowNumber() * 8 + colNumber()
  }

  fun rowNumber(): Int{
    var range = 0 until NUM_ROWS
    for(frontBack in rowBsp){
      range = if(frontBack == 'F'){
        firstHalf(range)
      }else {
        secondHalf(range)
      }
    }
    return (range.first + range.last) / 2
  }

  fun colNumber(): Int{
    var range = 0 until NUM_COLS
    for(rightLeft in columnBsp){
      range = if(rightLeft == 'L'){
        firstHalf(range)
      }else {
        secondHalf(range)
      }
    }
    return (range.first + range.last) / 2
  }

  private fun firstHalf(range: IntRange) = range.first .. (range.last - (range.count() / 2))
  private fun secondHalf(range: IntRange) = (range.first + (range.count() / 2)) .. range.last

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