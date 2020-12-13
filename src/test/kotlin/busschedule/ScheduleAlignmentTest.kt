package busschedule

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ScheduleAlignmentTest {
  @Test
  fun matchesTime_works() {
    assertTrue(worksAtTime( 0, Bus(7, 0)))
    assertTrue(worksAtTime( 7, Bus(7, 0)))
    assertFalse(worksAtTime( 1, Bus(7, 0)))
    assertTrue(worksAtTime( 25, Bus(13, 1)))
    assertTrue(worksAtTime( 1068781, Bus(59, 4)))
    assertTrue(worksAtTime( 32L, Bus(17, 36)))
  }

  @Test
  fun timeThatMatchesSchedule2_works() {
    val schedule = listOf(
      Bus(2L, 0L),
      Bus(5L, 1L),
    )

    assertEquals(4L, timeThatMatchesSchedule(schedule))
  }

  @Test
  fun timeThatMatchesSchedule3_works() {
    val schedule = listOf(
      Bus(5L, 0L),
      Bus(6L, 1L),
      Bus(7L, 2L),
    )

    assertEquals(5L, timeThatMatchesSchedule(schedule))
  }


  @Test
  fun timeThatMatchesSchedule_works() {
    val schedule = listOf(
      Bus(7L, 0L),
      Bus(13L, 1L),
      Bus(59L, 4L),
      Bus(31L, 6L),
      Bus(19L, 7L),
    )

    assertEquals(1068781L, timeThatMatchesSchedule(schedule))
  }

  @Test
  fun parseScheduleMap_works() {
    val schedule = "7,13,x,x,59,x,31,19"
    val expectedSchedule = listOf(
      Bus(7L, 0L),
      Bus(13L, 1L),
      Bus(59L, 4L),
      Bus(31L, 6L),
      Bus(19L, 7L),
    )
    assertEquals(expectedSchedule, parseScheduleMap(schedule))
  }
}