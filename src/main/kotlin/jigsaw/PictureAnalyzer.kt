package jigsaw

import com.google.common.collect.Sets
import kotlin.math.sqrt

fun waterRoughness(square: List<String>): Int {
  val grid = parseGrid(square)
  val fullPicture = Tile(0L, grid, square.size)
  val pictureRotations = fullPicture.configurations
  return pictureRotations
    .minOfOrNull {pic ->
      nonMonsterHashesCount(pic.pixels, pic.size)}!!
}

fun nonMonsterHashesCount(pixels: Map<Coord, Char>, size: Int): Int {
  val monsterCoords: Set<Coord> = findMonsterCoords(pixels, size)
  val hashCoords: Set<Coord> = pixels.filterValues { char -> char == '#'}.keys
  return Sets.difference(hashCoords, monsterCoords).size
}

fun findMonsterCoords(pixels: Map<Coord, Char>, size: Int): Set<Coord> {
  val result = mutableSetOf<Coord>()
  for(x in 0 until size){
    for(y in 0 until size){
      if(isHeadOfMonster(Coord(x, y), pixels)){
        result.addAll(coordsFromHead(Coord(x, y)))
      }
    }
  }
  return result
}

fun coordsFromHead(coord: Coord): Set<Coord> {
  val result = mutableSetOf(coord)
  result.addAll(secondRowExpectedCoords(coord))
  result.addAll(thirdRowExpectedCoords(coord))
  return result
}

fun isHeadOfMonster(coord: Coord, pixels: Map<Coord, Char>): Boolean {
  return pixels[coord] == '#' &&
      secondRowMatches(coord, pixels) &&
      thirdRowMatches(coord, pixels)
}

val middleRowOffsets = setOf(
  1, 0, -1, -6, -7, -12, -13, -18
)
val bottomRow = setOf(
  -2, -5, -8, -11, -14, -17
)

fun secondRowMatches(head: Coord, pixels: Map<Coord, Char>): Boolean {
  return secondRowExpectedCoords(head)
    .all{coord -> pixels[coord] == '#'}
}

fun thirdRowMatches(head: Coord, pixels: Map<Coord, Char>): Boolean {
  return thirdRowExpectedCoords(head)
    .all{coord -> pixels[coord] == '#'}
}

fun secondRowExpectedCoords(head: Coord): Set<Coord> {
  val y = head.y + 1
  return middleRowOffsets.map{offset ->
    Coord(head.x + offset, y)}.toSet()
}

fun thirdRowExpectedCoords(head: Coord): Set<Coord> {
  val y = head.y + 2
  return bottomRow.map{offset ->
    Coord(head.x + offset, y)}.toSet()
}


