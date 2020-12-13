package days

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day13Test {

  @Test
  fun nextBusAndWaitTime_works() {
    val departureTime = 939
    val schedule = listOf(7,13,59,31,19)
    val nextBus = nextBusAndWaitTime(departureTime, schedule)
    assertEquals(59, nextBus.busId)
    assertEquals(5, nextBus.waitTime)
  }

  @Test
  fun parseSchedule_works() {
    val schedule = "7,13,x,x,59,x,31,19"
    assertEquals(listOf(7,13,59,31,19), parseSchedule(schedule))
  }

  @Test
  fun matchesTime_works() {
    assertTrue(worksAtTime( 0, Pair(7, 0)))
    assertTrue(worksAtTime( 7, Pair(7, 0)))
    assertFalse(worksAtTime( 1, Pair(7, 0)))
    assertTrue(worksAtTime( 25, Pair(13, 1)))
    assertTrue(worksAtTime( 1068781, Pair(59, 4)))
    assertTrue(worksAtTime( 32L, Pair(17, 36)))
  }

  @Test
  fun timeThatMatchesSchedule2_works() {
    val schedule = mapOf(
      2 to 0,
      5 to 1,
    )

    assertEquals(4L, timeThatMatchesSchedule( schedule))
  }

  @Test
  fun timeThatMatchesSchedule3_works() {
    val schedule = mapOf(
      5 to 0,
      6 to 1,
      7 to 2,
    )

    assertEquals(5L, timeThatMatchesSchedule( schedule))
  }


  @Test
  fun timeThatMatchesSchedule_works() {
    val schedule = mapOf(
      7 to 0,
      13 to 1,
      59 to 4,
      31 to 6,
      19 to 7,
    )

    assertEquals(1068781L, timeThatMatchesSchedule( schedule))
  }

  @Test
  fun parseScheduleMap_works() {
    val schedule = "7,13,x,x,59,x,31,19"
    val expectedSchedule = mapOf(
      7 to 0,
      13 to 1,
      59 to 4,
      31 to 6,
      19 to 7,
    )
    assertEquals(expectedSchedule, parseScheduleMap(schedule))
  }

  @Test
  fun day13p1TestInput_works() {
    assertEquals(295, day13pt1("input/day13Test.txt"))
  }

  @Test
  fun day13p1_works() {
    assertEquals(259, day13pt1())
  }

  @Test
  fun day13p2TestInput_works() {
    assertEquals(1068781, day13pt2("input/day13Test.txt"))
  }

  @Test
  fun day13p2_works() {
    assertEquals(210612924879242, day13pt2())
  }
}