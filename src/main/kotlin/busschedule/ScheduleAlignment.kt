package busschedule

import org.apache.commons.math3.util.ArithmeticUtils

data class Schedule(val period: Long, val offset: Long)

fun timeThatMatchesSchedule(schedule: List<Schedule>): Long{
  if(schedule.size == 1){
    // Only needed to handle initial inputs of size 1
    // The general case returns at size 2
    return firstTimeThatWorks(schedule[0])
  }
  if(schedule.size == 2){
    return firstTimeThatWorksForBoth(schedule[0], schedule[1])
  }
  val chunks = schedule.chunked(2)
  val collapsed = chunks.map{ busList -> collapse(busList) }
  return timeThatMatchesSchedule(collapsed)
}

// When called with 2 schedules,
// returns a single schedule that represents the pair of schedule
// by their shared period (LCM) and offset
private fun collapse(scheduleList: List<Schedule>): Schedule {
  if(scheduleList.isEmpty() || scheduleList.size > 2){
    throw RuntimeException("collapse size ${scheduleList.size}")
  }
  if(scheduleList.size == 1){
    return scheduleList[0]
  }
  val schedule1 = scheduleList[0]
  val schedule2 = scheduleList[1]
  val period = ArithmeticUtils.lcm(schedule1.period, schedule2.period)
  val t = firstTimeThatWorksForBoth(schedule1, schedule2)
  return Schedule(period, period - t)
}

private fun firstTimeThatWorksForBoth(schedule1: Schedule, schedule2: Schedule): Long{
  val period = ArithmeticUtils.lcm(schedule1.period, schedule2.period)
  val busWithLargerPeriod = if(schedule1.period > schedule2.period) schedule1 else schedule2
  val firstPossibleTime = firstTimeThatWorks(busWithLargerPeriod)
  val lastPossibleTime = (period * 2)
  for (t in firstPossibleTime..lastPossibleTime step busWithLargerPeriod.period) {
    if(worksAtTime(t, schedule1) && worksAtTime(t, schedule2)){
      return t
    }
  }
  throw RuntimeException("firstTimeThatWorksForBoth failed")
}

fun worksAtTime(t: Long, schedule: Schedule): Boolean {
  return (t + schedule.offset) % schedule.period == 0L
}

//gross - figure out how to find the first time more easily
private fun firstTimeThatWorks(schedule: Schedule): Long {
  var startingT = schedule.period - schedule.offset
  while (startingT < 0 || !worksAtTime(startingT, schedule)) startingT++
  return startingT
}

fun parseScheduleMap(schedule: String): List<Schedule>{
  return schedule
    .split(",")
    .mapIndexedNotNull{idx, it ->
      if(it == "x"){
        null
      }else{
        Schedule(it.toLong(), idx.toLong())
      }
    }
}