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

  @Test
  fun day1p2TestInput_works() {
    assertEquals(241861950, day1pt2("input/day1Test.txt"))
  }

  @Test
  fun day1p2RealInput_works() {
    assertEquals(230057040, day1pt2("input/day1.txt"))
  }

  @Test
  fun day2p1TestInput_works() {
    assertEquals(2, day2pt1("input/day2Test.txt"))
  }

  @Test
  fun day2p2TestInput_works() {
    assertEquals(1, day2pt2("input/day2Test2.txt"))
  }

  @Test
  fun day3p1TestInput_works() {
    assertEquals(7, day3pt1("input/day3Test.txt"))
  }

  @Test
  fun day3p1_works() {
    assertEquals(193, day3pt1())
  }

  @Test
  fun day3p2TestInput_works() {
    assertEquals(336, day3pt2("input/day3Test.txt"))
  }
}