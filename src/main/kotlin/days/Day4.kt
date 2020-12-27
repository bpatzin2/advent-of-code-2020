package days

import fileinput.asString
import passport.parseBatchOfPassports
import passport.hasAllRequiredFields
import passport.isValid

fun day4pt1(pathname: String): Int {
  val passportBatch = asString(pathname)
  val passports = parseBatchOfPassports(passportBatch)
  return passports.filter{p -> hasAllRequiredFields(p) }.size
}

fun day4pt1(): Int {
  return day4pt1("input/day4.txt")
}

fun day4pt2(pathname: String): Int {
  val passportBatch = asString(pathname)
  val passports = parseBatchOfPassports(passportBatch)
  return passports.filter(::isValid).size
}

fun day4pt2(): Int {
  return day4pt2("input/day4.txt")
}