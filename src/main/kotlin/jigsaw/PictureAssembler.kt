package jigsaw

import kotlin.math.sqrt

fun assemble(tiles: List<Tile>): List<String> {
  val trimmedTiles = tiles.map { it.trimBorder() }
  return arrangeInSquare(trimmedTiles)
}

fun arrangeInSquare(tiles: List<Tile>): List<String> {
  val size = sqrt(tiles.size.toDouble()).toInt()
  val chunks = tiles.chunked(size)
  return chunks.flatMap { chunk -> tileRow(chunk) }
}

fun tileRow(tiles: List<Tile>): List<String> {
  val result = mutableListOf<String>()
  for(y in 0 until tiles[0].size){
    val row = mutableListOf<Char>()
    for(tileIdx in tiles.indices){
      val tile = tiles[tileIdx]
      for(x in 0 until tile.size) {
        val char = tile.pixels[Coord(x, y)]!!
        row.add(char)
      }
    }
    result.add(row.joinToString(""))
  }
  return result
}