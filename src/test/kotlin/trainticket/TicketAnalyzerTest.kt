package trainticket

import org.junit.Test
import kotlin.test.assertEquals

class TicketAnalyzerTest {
  @Test
  fun determineFieldOrder_trivial_works() {
    val rule1 = Rule("a", setOf(1..2))
    val rule2 = Rule("b", setOf(3..4))
    val input = TrainTicketNotes(
      setOf(rule1, rule2),
      setOf(listOf(1,3), listOf(2,4)),
      emptyList())
    val result = determineTicketFieldOrder(input)
    assertEquals(listOf("a", "b"), result)
  }

  @Test
  fun determineFieldOrder_complex_works() {
    val rule1 = Rule("a", setOf(1..2))
    val rule2 = Rule("b", setOf(3..4))
    val rule3 = Rule("c", setOf(2..3))
    val input = TrainTicketNotes(
      setOf(rule1, rule2, rule3),
      setOf(
        listOf(1, 3, 3),
        listOf(2, 3, 4)),
      emptyList())
    val result = determineTicketFieldOrder(input)
    assertEquals(listOf("a", "c", "b"), result)
  }

  @Test
  fun determineFieldOrder_testInputStr_works() {
    val testInputStr = """
      class: 0-1 or 4-19
      row: 0-5 or 8-19
      seat: 0-13 or 16-19

      your ticket:
      11,12,13

      nearby tickets:
      3,9,18
      15,1,5
      5,14,9
    """.trimIndent()
    val input = parseInput(testInputStr)
    val result = determineTicketFieldOrder(input)
    assertEquals(listOf("row", "class", "seat"), result)
  }
}