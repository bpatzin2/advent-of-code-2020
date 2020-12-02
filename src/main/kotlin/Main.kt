import fileinput.asDigitList
import fileinput.asNumberListByNewLine
import fileinput.toPasswordsWithPolicies
import findbysum.findPair
import findbysum.findTriplet
import inversecaptcha.inverseCaptcha
import passwordpolicy.isValid

fun day1From2017(): Long {
  val numberList = asDigitList("input/input2017Day1Pt1.txt")
  return inverseCaptcha(numberList)
}

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

fun day2pt1(pathname: String): Int {
  val passwordWithPolicies = toPasswordsWithPolicies(pathname)
  return passwordWithPolicies
    .filter(::isValid)
    .count()
}

fun main(){
  println(day1pt2())
}