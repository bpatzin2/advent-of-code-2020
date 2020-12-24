package days

import com.google.common.collect.Sets
import java.io.File

data class Coord(val x: Int, val y: Int)

data class Grid(var location: Coord, val flippedTiles: List<Coord>){
  fun move(lines: List<List<String>>): Grid{
    var currGrid = this
     for(line in lines){
       currGrid = currGrid.moveLine(line)
     }
    return currGrid
  }

  fun moveLine(dirs: List<String>): Grid{
    val original = location
    for(dir in dirs){
      location = nextLocation(location, dir)
    }
    val newFlipped = flippedTiles.toMutableList()
    newFlipped.add(location)
    return Grid(original, newFlipped)
  }

  fun countBlackTiles(): Int{
    return blackTiles().size
  }

  fun blackTiles(): Set<Coord>{
    val counts = flippedTiles.groupingBy { it }.eachCount()
    return counts.filter{(_, count) -> count % 2 == 1}.keys
  }

  fun nextDays(n: Int): Set<Coord>{
    var blackTiles = blackTiles()
    for(i in 0 until n){
      blackTiles = nextDay(blackTiles)
    }
    return blackTiles
  }
}

private fun nextDay(blackTiles: Set<Coord>): Set<Coord> {
  val cads = candidates(blackTiles)
  return cads.filter { applyRules(it, blackTiles) }.toSet()
}

fun applyRules(coord: Coord, blackTiles: Set<Coord>): Boolean {
  val neighbors = neighbors(coord)
  val blackNeighbors = Sets.intersection(neighbors, blackTiles)
  return if(blackTiles.contains(coord)){
    blackNeighbors.size in 1..2
  }else{
    blackNeighbors.size == 2
  }
}

fun candidates(blackTiles: Set<Coord>): Set<Coord>{
  return blackTiles.flatMap { blackTile ->
    val cads = mutableListOf(blackTile)
    cads.addAll(neighbors(blackTile))
    cads
  }.toSet()
}

fun neighbors(blackTile: Coord): Set<Coord> {
  return mutableSetOf(
    nextLocation(blackTile, "e"),
    nextLocation(blackTile, "ne"),
    nextLocation(blackTile, "se"),
    nextLocation(blackTile, "w"),
    nextLocation(blackTile, "nw"),
    nextLocation(blackTile, "sw"),
  )
}

fun nextLocation(location: Coord, dir: String): Coord{
  val (x, y) = location
  return when(dir){
    "e" -> location.copy(x = x + 2)
    "ne" -> location.copy(x = x + 1, y = y + 1)
    "se" -> location.copy(x = x + 1, y = y - 1)
    "w" -> location.copy(x = x - 2)
    "nw" -> location.copy(x = x - 1, y = y + 1)
    "sw" -> location.copy(x = x - 1, y = y - 1)
    else -> throw RuntimeException("unknown dir $dir")
  }
}

fun parseDirs(strs: List<String>): List<List<String>>{
  val result = mutableListOf<List<String>>()
  for(s in strs){
    val list = mutableListOf<String>()
    val cList = s.toList()
    var idx = 0
    while(idx < cList.size){
      if(cList[idx] == 'e' || cList[idx] == 'w'){
        list.add(cList[idx].toString())
        idx += 1
      }else{
        list.add(cList.subList(idx, idx+2).joinToString(""))
        idx += 2
      }
    }
    result.add(list)
  }
  return result
}

fun day24pt1(pathname: String): Int {
  val strs = File(pathname).readLines()
  val dirs = parseDirs(strs)
  return Grid(Coord(0,0), emptyList())
    .move(dirs)
    .countBlackTiles()
}

fun day24pt1(): Int {
  return day24pt1("input/day24.txt")
}

fun day24pt2(pathname: String): Int {
  val strs = File(pathname).readLines()
  val dirs = parseDirs(strs)
  return Grid(Coord(0,0), emptyList())
    .move(dirs)
    .nextDays(100).size
}

fun day24pt2(): Int {
  return day24pt2("input/day24.txt")
}

fun main() {
//  println("Part 1: " + day24pt1())
  println("Part 2: " + day24pt2())
}