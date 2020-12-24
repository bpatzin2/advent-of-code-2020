package days

import fileinput.asString
import jigsaw.*
import jigsaw.Coord
import java.io.File
import kotlin.math.sqrt

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

fun processAndWrite(pathname: String, outputPath: String) {
  val input = asString(pathname)
  val tiles = parseTiles(input)
  val size = sqrt(tiles.size.toDouble()).toInt()
  val pic = createPicture(tiles, size)!!
  writeToFile(outputPath, pic)
}

fun day20pt2(pathname: String): Int {
  val p1Output = outputFile(pathname)
  if(!File(p1Output).exists()){
    processAndWrite(pathname, p1Output)
  }
  val p2Input = asString(p1Output)
  val tiles = parseTiles(p2Input)
  val square: List<String> = assemble(tiles)
  // TODO: Don't hardcode this file name
  writeToFile("tmp/midpoint.txt", square)
  return waterRoughness(square)
}

fun day20pt2(): Int {
  return day20pt2("input/day20.txt")
}

fun main() {
  println("(takes 1min) Part 1: " + day20pt1())
  println("Part 2: " + day20pt2())
}