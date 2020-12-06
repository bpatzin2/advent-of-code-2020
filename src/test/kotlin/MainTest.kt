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
  fun day1p2_works() {
    assertEquals(230057040, day1pt2())
  }

  @Test
  fun day2p1TestInput_works() {
    assertEquals(2, day2pt1("input/day2Test.txt"))
  }

  @Test
  fun day2p1_works() {
    assertEquals(465, day2pt1())
  }

  @Test
  fun day2p2TestInput_works() {
    assertEquals(1, day2pt2("input/day2Test2.txt"))
  }

  @Test
  fun day2p2_works() {
    assertEquals(294, day2pt2())
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

  @Test
  fun day3p2_works() {
    assertEquals(1355323200, day3pt2())
  }

  @Test
  fun day4p1TestInput_works() {
    assertEquals(2, day4pt1("input/day4Test.txt"))
  }

  @Test
  fun day4p1_works() {
    assertEquals(254, day4pt1())
  }

  @Test
  fun day4p2TestInput_works() {
    assertEquals(2, day4pt2("input/day4Test.txt"))
  }

  @Test
  fun day4p2_works() {
    assertEquals(184, day4pt2())
  }

  @Test
  fun day5p1TestInput_works() {
    assertEquals(820, day5pt1("input/day5Test.txt"))
  }

  @Test
  fun day5p1_works() {
    assertEquals(850, day5pt1())
  }

  @Test
  fun day5p2_works() {
    assertEquals(599, day5pt2())
  }

  @Test
  fun day6p1TestInput_works() {
    assertEquals(11, day6pt1("input/day6Test.txt"))
  }

  @Test
  fun day6p1_works() {
    assertEquals(6521, day6pt1())
  }
}