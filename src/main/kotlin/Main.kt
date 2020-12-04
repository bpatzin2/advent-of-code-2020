import fileinput.asDigitList
import fileinput.asNumberListByNewLine
import fileinput.linesAsCharList
import fileinput.toPasswordsWithPolicies
import findbysum.findPair
import findbysum.findTriplet
import forest.countTreesOnPath
import inversecaptcha.inverseCaptcha
import passport.fromStringBatches
import passport.hasAllRequiredFields
import passwordpolicy.PasswordWithPolicy
import java.io.File

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
    .filter(PasswordWithPolicy::isValidWithMinMaxPolicy)
    .count()
}

fun day2pt1(): Int {
  return day2pt1("input/day2.txt")
}

fun day2pt2(pathname: String): Int {
  val passwordWithPolicies = toPasswordsWithPolicies(pathname)
  return passwordWithPolicies
    .filter(PasswordWithPolicy::isValidWithXorIndexPolicy)
    .count()
}

fun day2pt2(): Int {
  return day2pt2("input/day2.txt")
}

fun day3pt1(pathname: String): Int {
  val fileInput = linesAsCharList(pathname)
  val givenRight = 3
  val givenDown = 1
  return countTreesOnPath(
    fileInput, givenRight, givenDown)
}

fun day3pt1(): Int {
  return day3pt1("input/day3.txt")
}

fun day3pt2(pathname: String): Int {
  val givenRightDownSlopes = listOf(
    listOf(1, 1),
    listOf(3, 1),
    listOf(5, 1),
    listOf(7, 1),
    listOf(1, 2),
  )
  val fileInput = linesAsCharList(pathname)
  val treesPerSlope = givenRightDownSlopes.map{slope ->
    countTreesOnPath(fileInput, slope[0], slope[1])}
  return treesPerSlope.reduce(Int::times)
}

fun day3pt2(): Int {
  return day3pt2("input/day3.txt")
}

fun day4pt1(pathname: String): Int {
  val strList = File(pathname).readLines()
  val passports = fromStringBatches(strList)
  return passports.filter{p -> hasAllRequiredFields(p)}.size
}

fun day4pt1(): Int {
  return day4pt1("input/day4.txt")
}

fun main(args: Array<String>){
  if(!args.contains("--all")){
    println(day4pt1())
    return
  }
  println("Day 1 Part 1: " + day1pt1())
  println("Day 1 Part 2: " + day1pt2())
  println("Day 2 Part 1: " + day2pt1())
  println("Day 2 Part 2: " + day2pt2())
  println("Day 3 Part 1: " + day3pt1())
  println("Day 3 Part 2: " + day3pt2())
}