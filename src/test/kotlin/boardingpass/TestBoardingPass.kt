package boardingpass

import org.junit.Test
import kotlin.test.assertEquals

class TestBoardingPass {

  @Test
  fun fromString_works() {
    val boardingPass = fromString("FBFBBFFRLR")
    assertEquals("FBFBBFF", boardingPass.rowBsp)
    assertEquals("RLR", boardingPass.columnBsp)
  }

  @Test
  fun rowNumber_works() {
    val boardingPass = fromString("FBFBBFFRLR")
    assertEquals( 44, boardingPass.rowNumber())
  }

  @Test
  fun colNumber_works() {
    val boardingPass = fromString("FBFBBFFRLR")
    assertEquals( 5, boardingPass.colNumber())
  }

  @Test
  fun seatId_works() {
    val boardingPass = fromString("FBFBBFFRLR")
    assertEquals( 357, boardingPass.seatId())
  }

  @Test
  fun testData1_works() {
    test("BFFFBBFRRR", 70, 7, 567)
    test("FFFBBBFRRR", 14, 7, 119)
    test("BBFFBBFRLL", 102, 4, 820)
  }

  private fun test(boardingPassStr: String, row: Int, col: Int, seat: Int){
    val boardingPass = fromString(boardingPassStr)
    assertEquals( row, boardingPass.rowNumber())
    assertEquals( col, boardingPass.colNumber())
    assertEquals( seat, boardingPass.seatId())
  }
}