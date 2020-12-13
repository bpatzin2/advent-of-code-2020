package busschedule

import org.apache.commons.math3.util.ArithmeticUtils

data class Bus(val period: Long, val offset: Long)

fun timeThatMatchesSchedule(schedule: List<Bus>): Long{
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

// When called with 2 Buses,
// returns a single bus that represents the pair of buses
// by their shared period (LCM) and offset
private fun collapse(busList: List<Bus>): Bus {
  if(busList.isEmpty() || busList.size > 2){
    throw RuntimeException("collapse size ${busList.size}")
  }
  if(busList.size == 1){
    return busList[0]
  }
  val bus1 = busList[0]
  val bus2 = busList[1]
  val period = ArithmeticUtils.lcm(bus1.period, bus2.period)
  val t = firstTimeThatWorksForBoth(bus1, bus2)
  return Bus(period, period - t)
}

private fun firstTimeThatWorksForBoth(bus1: Bus, bus2: Bus): Long{
  val period = ArithmeticUtils.lcm(bus1.period, bus2.period)
  val busWithLargerPeriod = if(bus1.period > bus2.period) bus1 else bus2
  val firstPossibleTime = firstTimeThatWorks(busWithLargerPeriod)
  val lastPossibleTime = (period * 2)
  for (t in firstPossibleTime..lastPossibleTime step busWithLargerPeriod.period) {
    if(worksAtTime(t, bus1) && worksAtTime(t, bus2)){
      return t
    }
  }
  throw RuntimeException("firstTimeThatWorksForBoth failed")
}

fun worksAtTime(t: Long, bus: Bus): Boolean {
  return (t + bus.offset) % bus.period == 0L
}

//gross - figure out how to find the first time more easily
private fun firstTimeThatWorks(bus: Bus): Long {
  var startingT = bus.period - bus.offset
  while (startingT < 0 || !worksAtTime(startingT, bus)) startingT++
  return startingT
}

fun parseScheduleMap(schedule: String): List<Bus>{
  return schedule
    .split(",")
    .mapIndexedNotNull{idx, it ->
      if(it == "x"){
        null
      }else{
        Bus(it.toLong(), idx.toLong())
      }
    }
}