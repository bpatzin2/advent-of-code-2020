package days

import com.google.common.collect.Sets
import fileinput.asString
import mathhomework.*
import java.io.File
import kotlin.math.sqrt
import kotlin.RuntimeException

data class Coord(val x: Int, val y: Int)

data class ConfiguredTile(val id: Long, val config: String, val pixels: Map<Coord, Char>, val size: Int){
  val bottomRow: List<Char> by lazy {
    pixels
      .filterKeys{coord -> coord.y == (size-1)}
      .toSortedMap(compareBy { it.x })
      .values.toList()
  }

  val topRow: List<Char> by lazy {
    pixels
      .filterKeys{coord -> coord.y == 0}
      .toSortedMap(compareBy { it.x })
      .values
      .toList()
  }

  val leftRow: List<Char> by lazy {
    pixels
      .filterKeys{coord -> coord.x == 0}
      .toSortedMap(compareBy { it.y })
      .values
      .toList()
  }

  val rightRow: List<Char> by lazy {
    pixels
      .filterKeys{coord -> coord.x == (size-1)}
      .toSortedMap(compareBy { it.y })
      .values
      .toList()
  }
}

data class Tile(val id: Long, val pixels: Map<Coord, Char>, val size: Int){
  val configurations: List<ConfiguredTile> by lazy {
    val rots = getRotations()
    rots.flatMap { rot ->
      listOf(
        rot,
        ConfiguredTile(rot.id, rot.config + "fx", flipX(rot.pixels), size),
        ConfiguredTile(rot.id, rot.config + "fy", flipY(rot.pixels), size),
        ConfiguredTile(rot.id, rot.config + "fyfx", flipY(flipX(rot.pixels)), size),
      )
    }
  }

  private fun getRotations(): Set<ConfiguredTile> {
    val result = mutableListOf(ConfiguredTile(id, "0", pixels, size))
    for(i in 1..3){
      val toRotate = result.last().pixels
      val rotated = ConfiguredTile(id, "$i", rotateClockWise(toRotate), size)
      result.add(rotated)
    }
    return result.toSet()
  }

  fun flipX(matrix: Map<Coord, Char>):Map<Coord, Char>{
    val newLoc = newFlipLoc()
    return matrix.entries.map{(k, v) ->
      Coord(k.x, newLoc[k.y]!!) to v
    }.toMap()
  }

  fun flipY(matrix: Map<Coord, Char>):Map<Coord, Char>{
    val newLoc = newFlipLoc()
    return matrix.entries.map{(k, v) ->
      Coord(newLoc[k.x]!!, k.y) to v
    }.toMap()
  }

  private fun newFlipLoc(): MutableMap<Int, Int> {
    val newLoc = mutableMapOf<Int, Int>()
    ((0 until size) zip ( (size - 1) downTo 0)).map {
      newLoc[it.first] = it.second
    }
    return newLoc
  }

  fun rotateClockWise(matrix: Map<Coord, Char>):Map<Coord, Char> {
    val ret = mutableMapOf<Coord, Char>()
    for(y in 0 until size)
      for(x in 0 until size)
        ret[Coord(size - 1- x, y)] = matrix[Coord(y, x)]!!
    return ret
  }
}

fun fromLists(lists: List<List<Char>>): Map<Coord, Char>{
  val result = mutableMapOf<Coord, Char>()
  for(y in lists.indices)
    for(x in lists[0].indices)
      result[Coord(x, y)] = lists[y][x]
  return result
}

data class Picture(val tiles: Map<Coord, ConfiguredTile>, val size: Int){

  val tileIds: Set<Long> by lazy {
    tiles.values.map{it.id}.toSet()
  }

  override fun toString(): String {
    val sb = StringBuilder()
    sb.append(tiles.mapValues {(coord, tile) -> listOf(coord, tile.id, tile.config) }.values)
    return sb.toString()
  }

  fun placeTile(coord: Coord, tile: ConfiguredTile): Picture{
    val newTiles = tiles.toMutableMap()
    newTiles[coord] = tile
    return Picture(newTiles, size)
  }

  fun getUpNeighbor(coord: Coord): ConfiguredTile? {
    return tiles[Coord(coord.x, coord.y - 1)]
  }

  fun getLeftNeighbor(coord: Coord): ConfiguredTile? {
    return tiles[Coord(coord.x - 1, coord.y)]
  }
}

fun bottomTopAligned(topTile: ConfiguredTile, bottomTile: ConfiguredTile): Boolean {
  val bottomRow = topTile.bottomRow
  val topRow = bottomTile.topRow
  return bottomRow == topRow
}

fun leftRightAligned(rightTile: ConfiguredTile, leftTile: ConfiguredTile): Boolean {
  val leftRow = rightTile.leftRow
  val rightRow = leftTile.rightRow
  return leftRow == rightRow
}

fun createPicture(
  tiles: List<Tile>,
  size: Int,
): Picture? {
  val configuredTiles = tiles.flatMap { t -> t.configurations }.toSet()
  val rightCache = configuredTiles.map{tile ->
    val validRights = configuredTiles
      .filter{other -> other != tile}
      .filter{other -> leftRightAligned(other, tile)
      }
    tile to validRights.toSet()
  }.toMap()
  val bottomCache = configuredTiles.map{tile ->
    val validBottoms = configuredTiles
      .filter{other -> other != tile}
      .filter{other -> bottomTopAligned(tile, other)
      }
    tile to validBottoms.toSet()
  }.toMap()
  return createPicture(
    size,
    Picture(emptyMap(), size),
    configuredTiles,
    rightCache,
    bottomCache,
    mutableMapOf(),
  )
}

fun createPicture(
  size: Int,
  inProgressPic: Picture = Picture(emptyMap(), size),
  allTiles: Set<ConfiguredTile>,
  rightCache: Map<ConfiguredTile, Set<ConfiguredTile>>,
  bottomCache: Map<ConfiguredTile, Set<ConfiguredTile>>,
  fullCache: MutableMap<Pair<ConfiguredTile?, ConfiguredTile?>, Set<ConfiguredTile>>,
  ): Picture? {
  if(inProgressPic.tiles.size == (size * size)){
    return inProgressPic
  }
  val nextCoord = getNextCoord(size, inProgressPic)
  val eligiblePieces = getEligiblePieces(inProgressPic, nextCoord, allTiles, rightCache, bottomCache, fullCache)
  eligiblePieces.forEach{t ->
    val newInProgressPic = inProgressPic.placeTile(nextCoord, t)
    val result = createPicture(size, newInProgressPic, allTiles, rightCache, bottomCache, fullCache)
    if(result != null){
      return result
    }
  }
  return null
}

fun getEligiblePieces(
  inProgressPic: Picture,
  nextCoord: Coord,
  allTiles: Set<ConfiguredTile>,
  rightCache: Map<ConfiguredTile, Set<ConfiguredTile>>,
  bottomCache: Map<ConfiguredTile, Set<ConfiguredTile>>,
  fullCache: MutableMap<Pair<ConfiguredTile?, ConfiguredTile?>, Set<ConfiguredTile>>,
): Set<ConfiguredTile> {
  val eligiblePieces = getOrComputeEligiblePieces(inProgressPic, nextCoord, allTiles, rightCache, bottomCache, fullCache)
  return eligiblePieces
    .filter { !inProgressPic.tileIds.contains(it.id) }
    .filter { if(nextCoord.y != inProgressPic.size-1) bottomCache[it]!!.isNotEmpty() else true}
    .filter { if(nextCoord.x != inProgressPic.size-1) rightCache[it]!!.isNotEmpty() else true}
    .toSet()
}

fun getOrComputeEligiblePieces(
  inProgressPic: Picture,
  nextCoord: Coord,
  allTiles: Set<ConfiguredTile>,
  rightCache: Map<ConfiguredTile, Set<ConfiguredTile>>,
  bottomCache: Map<ConfiguredTile, Set<ConfiguredTile>>,
  fullCache: MutableMap<Pair<ConfiguredTile?, ConfiguredTile?>, Set<ConfiguredTile>>,
): Set<ConfiguredTile> {
  val upNeighbor = inProgressPic.getUpNeighbor(nextCoord)
  val leftNeighbor = inProgressPic.getLeftNeighbor(nextCoord)
  val pair = Pair(upNeighbor, leftNeighbor)
  val cachedResult = fullCache[pair]
  if(cachedResult != null) return cachedResult
  val result = computeEligibles(pair, allTiles, rightCache, bottomCache)
  fullCache[pair] = result
  return result
}

fun computeEligibles(
  pair: Pair<ConfiguredTile?, ConfiguredTile?>,
  allTiles: Set<ConfiguredTile>,
  rightCache: Map<ConfiguredTile, Set<ConfiguredTile>>,
  bottomCache: Map<ConfiguredTile, Set<ConfiguredTile>>,
): Set<ConfiguredTile>{
  if(pair.first == null && pair.second == null) return allTiles
  if(pair.first == null) return rightCache[pair.second]!!
  if(pair.second == null) return bottomCache[pair.first]!!
  return Sets.intersection(rightCache[pair.second]!!, bottomCache[pair.first]!!)
}

var startedTop = false
var startedLeft = false
var startedBottom = false
var startedRight = false
fun getNextCoord(size: Int, inProgressPic: Picture): Coord {
  for(x in 0 until size){
      val c = Coord(x, 0)
      if(!inProgressPic.tiles.containsKey(c)){
        if(!startedTop){
          startedTop = true
          println("starting top")
        }
        return c
      }
  }

  for(y in 0 until size){
    val c = Coord(0, y)
    if(!inProgressPic.tiles.containsKey(c)){
      if(!startedLeft){
        startedLeft = true
        println("starting left")
      }
      return c
    }
  }

  for(x in 0 until size){
    for(y in 0 until size){
      val c = Coord(x, y)
      if(!inProgressPic.tiles.containsKey(c)){
        return c
      }
    }
  }
  throw RuntimeException("NO NEXT COORD")
}

fun parseTile(str: String): Tile{
  val lines = str.split("\n")
  val id = lines[0].removePrefix("Tile ").removeSuffix(":").toInt()
  val tileRows = lines.drop(1)
  val result = mutableMapOf<Coord, Char>()
  for(row in tileRows.indices){
    val rowList = tileRows[row].toList()
    for(col in tileRows[row].indices){
      result[Coord(col, row)] = rowList[col]
    }
  }
  return Tile(id.toLong(), result, tileRows[0].length)
}

fun parseTiles(str: String): List<Tile> {
  val tiles = str.split("\n\n")
  return tiles.map{tile -> parseTile(tile)}
}

fun day20pt1(pathname: String): Long {
  val input = asString(pathname)
  val tiles = parseTiles(input)
  val size = sqrt(tiles.size.toDouble()).toInt()
  println("size: $size")
  val pic = createPicture(tiles, size)!!
  val tlId = pic.tiles[Coord(0, 0)]!!.id
  val trId = pic.tiles[Coord(size-1, 0)]!!.id
  val blId = pic.tiles[Coord(0, size-1)]!!.id
  val brId = pic.tiles[Coord(size-1, size-1)]!!.id
  return tlId * trId * blId * brId
}

fun day20pt1(): Long {
  return day20pt1("input/day20.txt")
}

fun day20pt2(pathname: String): Long {
  val expressions = File(pathname).readLines()
  return evalWithAdditionPrecedenceAndSum(expressions)
}

fun day20pt2(): Long {
  return day20pt2("input/day20.txt")
}

fun main() {
  println("Part 1: " + day20pt1())
//  println("Part 2: " + day20pt2())
}