package days

import passport.fromStringBatches
import passport.hasAllRequiredFields
import passport.isValid
import java.io.File

fun day4pt1(pathname: String): Int {
  val strList = File(pathname).readLines()
  val passports = fromStringBatches(strList)
  return passports.filter{p -> hasAllRequiredFields(p) }.size
}

fun day4pt1(): Int {
  return day4pt1("input/day4.txt")
}

fun day4pt2(pathname: String): Int {
  val strList = File(pathname).readLines()
  val passports = fromStringBatches(strList)
  return passports.filter(::isValid).size
}

fun day4pt2(): Int {
  return day4pt2("input/day4.txt")
}