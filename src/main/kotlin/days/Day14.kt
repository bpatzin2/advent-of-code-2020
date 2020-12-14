package days

import decoder.parseInput
import decoder.updateMemoryBitMaskedAndSum
import decoder.updateMemoryDecoderAndSum
import java.io.File

fun day14pt1(pathname: String): Long {
  val strList = File(pathname).readLines()
  val maskAndInstructions = parseInput(strList)
  return updateMemoryBitMaskedAndSum(maskAndInstructions)
}

fun day14pt1(): Long {
  return day14pt1("input/day14.txt")
}

fun day14pt2(pathname: String): Long {
  val strList = File(pathname).readLines()
  val maskAndInstructions = parseInput(strList)
  return updateMemoryDecoderAndSum(maskAndInstructions)
}

fun day14pt2(): Long {
  return day14pt2("input/day14.txt")
}

fun main() {
  println("Part 1: " + day14pt1())
  println("Part 2: " + day14pt2())
}