package days

import fileinput.asNumberListByNewLine
import findbysum.findPair
import findbysum.findTriplet

fun day1pt1(): Int {
  return day1pt1("input/day1.txt")
}

fun day1pt2(): Int {
  return day1pt2("input/day1.txt")
}

fun day1pt1(pathname: String): Int {
  val numberList = asNumberListByNewLine(pathname)
  val pair = findPair(numberList, 2020) ?: return -1
  return pair.first * pair.second
}

fun day1pt2(pathname: String): Int {
  val numberList = asNumberListByNewLine(pathname)
  val list = findTriplet(numberList, 2020) ?: return -1
  return list.reduce(Int::times)
}