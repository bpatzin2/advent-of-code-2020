import org.junit.Test
import kotlin.test.assertEquals

class InverseCaptchaTest {
  @Test
  fun whenEmptyList_thenAnswerIs0() {
    assertEquals(0, inverseCaptcha(listOf()))
  }

  @Test
  fun whenNoMatchesInList_thenAnswerIs0() {
    assertEquals(0, inverseCaptcha(listOf(1,2)))
  }

  @Test
  fun whenSingleElementList_thenAnswerIsThatElement() {
    assertEquals(1, inverseCaptcha(listOf(1)))
  }

  @Test
  fun whenListHasOnePair_thenAnswerIsElementValue() {
    assertEquals(1, inverseCaptcha(listOf(1,1,0)))
  }

  @Test
  fun whenListHasOnePairAndEndMatchesStart_thenAnswerIsPairElementPlusStartValue() {
    assertEquals(3, inverseCaptcha(listOf(2,1,1,2)))
  }
}