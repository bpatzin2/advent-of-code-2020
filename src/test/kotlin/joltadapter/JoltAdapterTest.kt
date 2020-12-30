package joltadapter

import org.junit.Test
import kotlin.test.assertEquals

class JoltAdapterTest {
  private val testInts = listOf(
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
}