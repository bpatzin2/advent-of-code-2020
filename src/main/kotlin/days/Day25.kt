package days

import java.io.File

fun determineLoopSize(publicKey: Long, subjectNumber: Long): Long{
  var current = 1L
  var loopSize = 0L
  while(current != publicKey){
    current *= subjectNumber
    current %= 20201227
    loopSize++
  }
  return loopSize
}

fun calculateEncryptionKey(keyA: Long, loopSizeB: Long): Long{
  var encryptionKey = 1L
  for(i in 0 until loopSizeB){
    encryptionKey *= keyA
    encryptionKey %= 20201227
  }
  return encryptionKey
}

fun day25pt1(pathname: String): Long {
  val publicKeys = File(pathname).readLines().map(String::toLong)
  val loopSizeA = determineLoopSize(publicKeys[0], 7)
  return calculateEncryptionKey(publicKeys[1], loopSizeA)
}

fun day25pt1(): Long {
  return day25pt1("input/day25.txt")
}

fun main() {
  println("Part 1: " + day25pt1())
}