package days

import busschedule.nextBusAndWaitTime
import busschedule.parseSchedule
import busschedule.parseScheduleMap
import busschedule.timeThatMatchesSchedule
import java.io.File

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