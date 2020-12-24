package days

import fileinput.asString
import jigsaw.*
import org.junit.Test
import java.io.File
import kotlin.math.sqrt
import kotlin.test.assertEquals

// TODO: Clean up the tests in this file
// Allow better support tests based off of intermediate output files
class Day20Test {
  @Test
  fun day20p1TestInput_createPic_works() {
    val input = asString("input/day20Test.txt")
    val tiles = parseTiles(input)
    val size = sqrt(tiles.size.toDouble())
    val pic: Picture = createPicture(tiles, size.toInt())!!
    val resultTiles: Map<jigsaw.Coord, ConfiguredTile> = pic.tiles
    assertEquals(1951L, resultTiles[jigsaw.Coord(0, 0)]!!.id)
    assertEquals(2311L, resultTiles[jigsaw.Coord(1, 0)]!!.id)
    assertEquals(3079L, resultTiles[jigsaw.Coord(2, 0)]!!.id)
    assertEquals(2729L, resultTiles[jigsaw.Coord(0, 1)]!!.id)
    assertEquals(1427L, resultTiles[jigsaw.Coord(1, 1)]!!.id)
    assertEquals(2473L, resultTiles[jigsaw.Coord(2, 1)]!!.id)
    assertEquals(2971L, resultTiles[jigsaw.Coord(0, 2)]!!.id)
    assertEquals(1489L, resultTiles[jigsaw.Coord(1, 2)]!!.id)
    assertEquals(1171L, resultTiles[jigsaw.Coord(2, 2)]!!.id)
  }

  @Test
  fun day20p1TestInput_works() {
    assertEquals(20899048083289, day20pt1("input/day20Test.txt"))
  }

  @Test
  fun day20p1_works() {
//    Works but takes > 1 min
//    assertEquals(20033377297069 , day20pt1())
  }

  @Test
  fun day20p2Midpoint_works() {
    val tmpFile = File("tmp/midpoint.txt")
    if(tmpFile.exists()){
      tmpFile.delete()
    }

    day20pt2("input/day20Test.txt")
    assertEquals(
      asString("input/day20Midpoint.txt"),
      asString("tmp/midpoint.txt"));
  }

  @Test
  fun day20p2TestInput_works() {
    assertEquals(273, day20pt2("input/day20Test.txt"))
  }

  @Test
  fun day20p2_works() {
    assertEquals(2084, day20pt2())
  }
}