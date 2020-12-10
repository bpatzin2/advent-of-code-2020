package days

import org.junit.Test
import kotlin.test.assertEquals

class Day10Test {

  val testInts = listOf(
    16,
    10,
    15,
    5,
    1,
    11,
    7,
    19,
    6,
    12,
    4,
  )

  @Test
  fun joltDifferences_works() {
    assertEquals(mapOf(1 to 7, 3 to 5), joltDifferences(testInts))
  }

  @Test
  fun oneJoltsTimes3Jolts_works() {
    assertEquals(35, oneJoltsTimes3Jolts(testInts))
  }

  @Test
  fun day10p1TestInput_works() {
    assertEquals(220, day10pt1("input/day10Test.txt"))
  }

  @Test
  fun day10p1_works() {
  assertEquals(2380, day10pt1())
  }

  @Test
  fun countArrangementsSingleElement_works() {
    assertEquals(1, countArrangements(listOf(1)))
  }

  @Test
  fun countArrangementsTwoElement_works() {
    assertEquals(2, countArrangements(listOf(1 , 2)))
  }

  @Test
  fun arrangementsTwoElement_works() {
    assertEquals(
      2,
      countArrangements(listOf(1, 2)))
  }

  @Test
  fun countArrangementsThreeElement_works() {
    assertEquals(4, countArrangements(listOf(1 , 2, 3)))
  }

  @Test
  fun arrangementsFourElement_works() {
    //(1, 2, 4, 5)
    //(1, 2, 5)
    //(1, 4, 5)
    //(2, 4, 5)
    //(2, 5)
    assertEquals(5L,
      countArrangements(listOf(1, 2, 4, 5))
    )
  }

  @Test
  fun countArrangements_works() {
    assertEquals(8, countArrangements(testInts))
  }

  @Test
  fun day10p2TestInput_works() {
    assertEquals(19208, day10pt2("input/day10Test.txt"))
  }

  @Test
  fun day10p2_works() {
    assertEquals(48358655787008, day10pt2())
  }
}