package days

import passwordpolicy.PasswordWithPolicy
import passwordpolicy.parsePasswordWithPolicy
import java.io.File

fun day2pt1(pathname: String): Int {
  val passwordWithPolicies = parsePasswordWithPolicies(pathname)
  return passwordWithPolicies
    .filter(PasswordWithPolicy::isValidWithMinMaxPolicy)
    .count()
}

fun day2pt1(): Int {
  return day2pt1("input/day2.txt")
}

fun day2pt2(pathname: String): Int {
  val passwordWithPolicies = parsePasswordWithPolicies(pathname)
  return passwordWithPolicies
    .filter(PasswordWithPolicy::isValidWithXorIndexPolicy)
    .count()
}

fun day2pt2(): Int {
  return day2pt2("input/day2.txt")
}

fun parsePasswordWithPolicies(pathname: String): List<PasswordWithPolicy> {
  val strList = File(pathname).readLines()
  return strList.map(::parsePasswordWithPolicy)
}