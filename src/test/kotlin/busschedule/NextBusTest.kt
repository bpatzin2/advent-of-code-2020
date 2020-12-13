package busschedule

import org.junit.Test
import kotlin.test.assertEquals

class NextBusTest {
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
}