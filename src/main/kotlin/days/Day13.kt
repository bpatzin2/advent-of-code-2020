package days

import java.io.File
import org.apache.commons.math3.util.ArithmeticUtils

data class NextBus(val busId: Int, val waitTime: Int)

fun nextBusAndWaitTime(departureTime: Int, schedule: List<Int>): NextBus {
  val nextArrivalTimes = schedule.map {
    val nextArrival = ((departureTime / it) + 1) * it
    NextBus(it, nextArrival - departureTime)
  }
  return nextArrivalTimes.minByOrNull { bus -> bus.waitTime }!!
}

fun timeThatMatchesSchedule(schedule: Map<Int, Int>): Long {
  val schedulePairs = schedule
    .map{(k, v) -> Pair(k.toLong(), v.toLong())}
    .sortedBy { it.second }
  return timeThatMatchesRecur(schedulePairs)
}

fun timeThatMatchesRecur(schedule: List<Pair<Long, Long>>): Long{
  if(schedule.size == 2){
    return finalAnswer(schedule[0], schedule[1])
  }
  val chunks = schedule.chunked(2)
  val collapsed = chunks.map{ busList -> collapse(busList)}
  return timeThatMatchesRecur(collapsed)
}

fun collapse(busList: List<Pair<Long, Long>>): Pair<Long, Long> {
  if(busList.isEmpty() || busList.size > 2){
    throw RuntimeException("collapse size ${busList.size}")
  }
  if(busList.size == 1){
    return busList[0]
  }
  val bus1 = busList[0]
  val bus2 = busList[1]
  val period = ArithmeticUtils.lcm(bus1.first, bus2.first)
  val smallBus = if(bus1.first > bus2.first) bus2 else bus1

  //gross - figure out how to find the first time more easily
  var startingT = smallBus.first - smallBus.second
  while(startingT < 0 || !worksAtTime(startingT, smallBus)) startingT++

  for(t in startingT..(period * 2) step smallBus.first){
    if(t < 0) continue
    if(worksAtTime(t, bus1) && worksAtTime(t, bus2) ){
      return Pair(period, period - t)
    }
  }

  throw RuntimeException("collapse failed")
}

fun finalAnswer(bus1: Pair<Long, Long>, bus2: Pair<Long, Long>): Long{
  val stepBus = if(bus1.first > bus2.first) bus1 else bus2
  for(step in 1..Long.MAX_VALUE){
    val t = (step * stepBus.first) - stepBus.second
    if(worksAtTime(t, bus1) && worksAtTime(t, bus2) ){
      return t
    }
  }
  throw RuntimeException("finalAnswer failed")
}

fun worksAtTime(t: Long, bus: Pair<Long, Long>): Boolean {
  return (t + bus.second) % bus.first == 0L
}

fun parseSchedule(schedule: String): List<Int>{
  return schedule
    .split(",")
    .filter{it != "x"}
    .map{it.toInt()}
}

fun parseScheduleMap(schedule: String): Map<Int, Int>{
  return schedule
    .split(",")
    .mapIndexedNotNull{idx, it ->
      if(it == "x"){
        null
      }else{
        it.toInt() to idx
      }
    }.toMap()
}


fun day13pt1(pathname: String): Int {
  val strList = File(pathname).readLines()
  val departureTime = strList[0].toInt()
  val schedule = parseSchedule(strList[1])
  val nextBust = nextBusAndWaitTime(departureTime, schedule)
  return nextBust.busId * nextBust.waitTime
}

fun day13pt1(): Int {
  return day13pt1("input/day13.txt")
}

fun day13pt2(pathname: String): Long {
  val strList = File(pathname).readLines()
  val schedule = parseScheduleMap(strList[1])
  return timeThatMatchesSchedule(schedule)
}

fun day13pt2(): Long {
  return day13pt2("input/day13.txt")
}

fun main() {
  println("Part 1: " + day13pt1())
  println("Part 2: " + day13pt2())
}