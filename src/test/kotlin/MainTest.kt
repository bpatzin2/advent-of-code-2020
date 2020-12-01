import org.junit.Test
import kotlin.test.assertEquals

class MainTest {
  @Test
  fun day1From2017_works() {
    assertEquals(1141, day1From2017())
  }

  @Test
  fun day1p1TestInput_works() {
    assertEquals(514579, day1pt1("input/day1Test.txt"))
  }

  @Test
  fun day1p1RealInput_works() {
    assertEquals(969024, day1pt1())
  }
}