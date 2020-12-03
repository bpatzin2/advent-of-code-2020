package findbysum

import org.junit.Test
import kotlin.test.assertEquals

class FindBySumTest {
  val SAMPLE_INPUT = listOf(1721, 979, 366, 299, 675, 1456)

  @Test
  fun findPair_whenEmptyList_thenAnswerIsNull() {
    assertEquals(null, findPair(listOf(), 42))
  }

  @Test
  fun findPair_whenSize1List_thenAnswerIsNull() {
    assertEquals(null, findPair(listOf(1), 42))
  }

  @Test
  fun findPair_whenThereIsNoPairThatSums_thenAnswerIsNull() {
    assertEquals(null, findPair(listOf(1, 2), 42))
  }

  @Test
  fun findPair_checkForHalf_thenAnswerIsNull() {
    assertEquals(null, findPair(listOf(21, 2, 3), 42))
  }

  @Test
  fun findPair_whenThereIsAPairThatSums_thenAnswerIsThatPair() {
    val expected: Set<Int> = setOf(2, 40)
    val actualPair = findPair(listOf(1, 2, 3, 40), 42)!!
    val actualSet: Set<Int> = actualPair.toList().toSet()
    assertEquals(expected, actualSet)
  }

  @Test
  fun findTriplet_whenEmptyList_thenAnswerIsNull() {
    assertEquals(null, findTriplet(listOf(), 42))
  }

  @Test
  fun findTriplet_whenSize2List_thenAnswerIsNull() {
    assertEquals(null, findTriplet(listOf(1, 41), 42))
  }

  @Test
  fun findTriplet_whenThereIsNoTripletThatSums_thenAnswerIsNull() {
    assertEquals(null, findTriplet(listOf(1, 2, 41), 42))
  }

  @Test
  fun findTriplet_whenThereATripletThatSums_thenAnswerThatTriplet() {
    assertEqualContent(listOf(1, 2, 39), findTriplet(listOf(1, 2, 39), 42)!!)
  }

  @Test
  fun findTriplet_sampleInputTest_thenAnswerThatTriplet() {
    val expectedResult = listOf(979, 366, 675)
    val actualResult = findTriplet(SAMPLE_INPUT, 2020)!!
    println(actualResult)
    assertEqualContent(expectedResult, actualResult)
  }

  private fun <T>assertEqualContent(l1: List<T>, l2: List<T>){
    assert(l1.containsAll(l2))
    assert(l2.containsAll(l1))
  }
}