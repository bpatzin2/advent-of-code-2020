package busschedule

data class NextBus(val busId: Int, val waitTime: Int)

fun nextBusAndWaitTime(departureTime: Int, schedule: List<Int>): NextBus {
  val nextArrivalTimes = schedule.map {
    val nextArrival = ((departureTime / it) + 1) * it
    NextBus(it, nextArrival - departureTime)
  }
  return nextArrivalTimes.minByOrNull { bus -> bus.waitTime }!!
}

fun parseSchedule(schedule: String): List<Int>{
  return schedule
    .split(",")
    .filter{it != "x"}
    .map{it.toInt()}
}