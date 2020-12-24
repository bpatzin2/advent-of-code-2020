package jigsaw

fun parseTile(str: String): Tile{
  val lines = str.split("\n")
  val id = lines[0].removePrefix("Tile ").removeSuffix(":").toInt()
  val tileRows = lines.drop(1)
  val result = parseGrid(tileRows)
  return Tile(id.toLong(), result, tileRows[0].length)
}

fun parseGrid(tileRows: List<String>): MutableMap<Coord, Char> {
  val result = mutableMapOf<Coord, Char>()
  for (row in tileRows.indices) {
    val rowList = tileRows[row].toList()
    for (col in rowList.indices) {
      result[Coord(col, row)] = rowList[col]
    }
  }
  return result
}

fun parseTiles(str: String): List<Tile> {
  val tiles = str.split("\n\n")
  return tiles.map{tile -> parseTile(tile)}
}