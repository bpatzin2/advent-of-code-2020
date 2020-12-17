package conway

import com.google.common.collect.Lists
import org.paukov.combinatorics3.Generator

const val ALIVE = '#'
typealias Coord = List<Long>

fun nextState(prevState: Set<Coord>): Set<Coord> {
  if(prevState.isEmpty()){
    return emptySet()
  }

  val pointsToExplore = getPointsToExplore(prevState)
  return pointsToExplore.filter{ nextState(it, prevState) }.toSet()
}

fun parse(strList: List<String>, numDimensions: Int): Set<Coord>{
  val result = mutableSetOf<Coord>()
  strList.mapIndexed{rowNum, row ->
    row.mapIndexed{colNum, cell ->
      if(cell == ALIVE){
        val twoPoint = listOf(colNum.toLong(), rowNum.toLong())
        result.add(padList(twoPoint, numDimensions))
      }
    }
  }
  return result
}

fun nextState(coord: Coord, prevState: Set<Coord>): Boolean {
  val neighbors: Set<Coord> = getNeighbors(coord)
  val neighborsInPrevState = neighbors.filter { prevState.contains(it) }
  if(prevState.contains(coord)){
    return (2..3).contains(neighborsInPrevState.size)
  }
  //was inactive
  return neighborsInPrevState.size == 3
}

private fun getPointsToExplore(prevState: Set<Coord>): List<List<Long>> {
  val point = prevState.first()
  val dimensionRanges = point.indices.map { i -> dimensionRangeToExplore(prevState.map { it[i] }) }
  return Lists.cartesianProduct(dimensionRanges)
}

private fun createRelative(coord: Coord, rel: List<Int>): Coord{
  return (coord zip rel).map{(a, b) -> a+b}
}

private fun getNeighbors(coord: Coord): Set<Coord> {
  val result = mutableSetOf<Coord>()
  val directions = Generator.permutation(0, 1, -1)
    .withRepetitions(coord.size)
    .stream()

  for(dir in directions){
    if (dir.all{it == 0}) continue
    val c = createRelative(coord, dir)
    result.add(c)
  }
  return result
}

private fun dimensionRangeToExplore(points: Collection<Long>): List<Long>{
  val pointRange = range(points)
  return ((pointRange.start - 1)..(pointRange.last + 1)).toList()
}

private fun range(longs: Collection<Long>): LongRange{
  val min = longs.minOrNull()!!
  val max = longs.maxOrNull()!!
  return min..max
}

private fun padList(list: List<Long>, desiredSize: Int): List<Long>{
  val result = list.toMutableList()
  while(result.size < desiredSize){
    result.add(0)
  }
  return result
}