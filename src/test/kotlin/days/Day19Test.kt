package days

import messagerules.*
import org.junit.Test
import kotlin.test.assertEquals

class Day19Test {
  @Test
  fun parseRules_works(){
    val rules = """
      0: 4 1 5
      3: 4 5 | 5 4
      5: "b"
    """.trimIndent().split("\n")

    val result = parseRules(rules)
    assertEquals(createOptionList(listOf(4L, 1L, 5L)), result[0L])
    assertEquals(createOptionList(listOf(4L, 5L), listOf(5L, 4L)), result[3L])
    assertEquals(CharRule('b'), result[5L])
  }

  @Test
  fun day19p1TestInput_works() {
    assertEquals(2, day19pt1("input/day19Test.txt"))
  }

  @Test
  fun day19p1_works() {
    assertEquals(198, day19pt1())
  }

  @Test
  fun day19p2_works() {
    assertEquals(372, day19pt2())
  }
}