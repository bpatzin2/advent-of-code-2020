package days

import org.junit.Test
import trainticket.parseNearbyTickets
import trainticket.parseRulesRanges
import kotlin.test.assertEquals

class Day16Test {

  @Test
  fun sumInvalidValues_works() {
    val ruleStrs = """
      class: 1-3 or 5-7
      row: 6-11 or 33-44
      seat: 13-40 or 45-50
    """.trimIndent().split("\n")

    val nearbyTicketStrs = """
      7,3,47
      40,4,50
      55,2,20
      38,6,12
    """.trimIndent().split("\n")

    val result = sumInvalidValues(
      parseNearbyTickets(nearbyTicketStrs),
      parseRulesRanges(ruleStrs)
    )
    assertEquals(71, result)
  }

  @Test
  fun day16p1TestInput_works() {
    assertEquals(71, day16pt1("input/day16Test.txt"))
  }

  @Test
  fun day16p1_works() {
    assertEquals(32842, day16pt1())
  }

  // No good pt2 test data

  @Test
  fun day16p2_works() {
    assertEquals(2628667251989, day16pt2())
  }
}