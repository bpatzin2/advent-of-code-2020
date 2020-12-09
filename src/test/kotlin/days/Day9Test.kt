package days

import org.junit.Test
import kotlin.test.assertEquals

class Day9Test {

  val testInts = """
    35
    20
    15
    25
    47
    40
    62
    55
    65
    95
    102
    117
    150
    182
    127
    219
    299
    277
    309
    576
  """.trimIndent()

  @Test
  fun firstNotSumOfPrevious_works() {
    val longs = testInts.split("\n").map{it.toLong()}
    assertEquals(127,
      firstNotSumOfPrevious(longs, 5))
  }

  @Test
  fun day9p1TestInput_works() {
    assertEquals(127, day9pt1("input/day9Test.txt", 5))
  }

  @Test
  fun day9p1_works() {
    assertEquals(18272118, day9pt1())
  }


  @Test
  fun findContiguousSet_works() {
    val longs = testInts.split("\n").map{it.toLong()}
    assertEquals(
      setOf(15L, 25L, 47L, 40L),
      findContiguousSet(longs, 127))
  }

  @Test
  fun day9p2TestInput_works() {
    assertEquals(62, day9pt2("input/day9Test.txt", 127))
  }

  @Test
  fun day9p2_works() {
    assertEquals(2186361, day9pt2())
  }
}