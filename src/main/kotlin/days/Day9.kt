package days

import com.google.common.collect.Sets
import java.io.File

fun isASumOfPair(num: Long, ints: List<Long>): Boolean{
  val pairs = Sets.combinations(ints.toSet(), 2)
  return pairs
    .any{pair -> pair.reduce(Long::plus) == num}
}

fun firstNotSumOfPrevious(ints: List<Long>, prevCount: Int): Long {
  for(idx in prevCount until ints.size){
    val currVal = ints[idx]
    val preamble = ints.subList(idx-prevCount, idx)
    if(!isASumOfPair(currVal, preamble)){
      return currVal
    }
  }
  return -1
}

fun findContiguousSet(ints: List<Long>, sum: Long): Set<Long> {
  for(setSize in 2 until ints.size){
    for(set in ints.windowed(size = setSize, step = 1)) {
      if(set.reduce(Long::plus) == sum){
        return set.toSet()
      }
    }
  }
  return emptySet()
}

fun day9pt1(pathname: String, prevCount: Int): Long {
  val strList = File(pathname).readLines()
  val longs = strList.map {it.toLong()}
  return firstNotSumOfPrevious(longs, prevCount)
}

fun day9pt1(): Long {
  return day9pt1("input/day9.txt", 25)
}

fun day9pt2(pathname: String, sum: Long): Long {
  val strList = File(pathname).readLines()
  val longs = strList.map {it.toLong()}
  val set = findContiguousSet(longs, sum)
  return set.minOrNull()!! + set.maxOrNull()!!
}

fun day9pt2(): Long {
  return day9pt2("input/day9.txt", 18272118L)
}

fun main() {
  println("Part 1: " + day9pt1())
  println("Part 2: " + day9pt2())
}