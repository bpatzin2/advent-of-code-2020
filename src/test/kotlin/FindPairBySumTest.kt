import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class FindPairBySumTest {
  @Test
  fun whenEmptyList_thenAnswerIsNull() {
    assertEquals(null, find(listOf(), 42))
  }

  @Test
  fun whenSize1List_thenAnswerIsNull() {
    assertEquals(null, find(listOf(1), 42))
  }

  @Test
  fun whenThereIsNoPairThatSums_thenAnswerIsNull() {
    assertEquals(null, find(listOf(1, 2), 42))
  }

  @Test
  fun whenThereIsAPairThatSums_thenAnswerIsThatPair() {
    val expected: Set<Int> = setOf(2, 40)
    val actualPair = find(listOf(1, 2, 3, 40), 42)
    if(actualPair == null){
      fail("Unexpected null result")
    }
    val actualSet: Set<Int> = actualPair.toList().toSet()
    assertEquals(expected, actualSet)
  }
}