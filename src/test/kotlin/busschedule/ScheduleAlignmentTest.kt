package busschedule

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ScheduleAlignmentTest {
  @Test
  fun matchesTime_works() {
    assertTrue(worksAtTime( 0, Schedule(7, 0)))
    assertTrue(worksAtTime( 7, Schedule(7, 0)))
    assertFalse(worksAtTime( 1, Schedule(7, 0)))
    assertTrue(worksAtTime( 25, Schedule(13, 1)))
    assertTrue(worksAtTime( 1068781, Schedule(59, 4)))
    assertTrue(worksAtTime( 32L, Schedule(17, 36)))
  }

  @Test
  fun timeThatMatchesSchedule2_works() {
    val schedule = listOf(
      Schedule(2L, 0L),
      Schedule(5L, 1L),
    )

    assertEquals(4L, timeThatMatchesSchedule(schedule))
  }

  @Test
  fun timeThatMatchesSchedule3_works() {
    val schedule = listOf(
      Schedule(5L, 0L),
      Schedule(6L, 1L),
      Schedule(7L, 2L),
    )

    assertEquals(5L, timeThatMatchesSchedule(schedule))
  }


  @Test
  fun timeThatMatchesSchedule_works() {
    val schedule = listOf(
      Schedule(7L, 0L),
      Schedule(13L, 1L),
      Schedule(59L, 4L),
      Schedule(31L, 6L),
      Schedule(19L, 7L),
    )

    assertEquals(1068781L, timeThatMatchesSchedule(schedule))
  }

  @Test
  fun parseScheduleMap_works() {
    val schedule = "7,13,x,x,59,x,31,19"
    val expectedSchedule = listOf(
      Schedule(7L, 0L),
      Schedule(13L, 1L),
      Schedule(59L, 4L),
      Schedule(31L, 6L),
      Schedule(19L, 7L),
    )
    assertEquals(expectedSchedule, parseScheduleMap(schedule))
  }
}