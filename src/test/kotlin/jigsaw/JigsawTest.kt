package jigsaw

import org.junit.Test
import kotlin.test.assertEquals

class JigsawTest {
  val r0 = fromLists(listOf(
    listOf('#', '.', '.'),
    listOf('.', '.', '.'),
    listOf('.', '.', '.'),
  ))
  val r1 = fromLists(listOf(
    listOf('.', '.', '#'),
    listOf('.', '.', '.'),
    listOf('.', '.', '.'),
  ))
  val r2 = fromLists(listOf(
    listOf('.', '.', '.'),
    listOf('.', '.', '.'),
    listOf('.', '.', '#'),
  ))
  val r3 = fromLists(listOf(
    listOf('.', '.', '.'),
    listOf('.', '.', '.'),
    listOf('#', '.', '.'),
  ))

  val s0 = fromLists(listOf(
    listOf('#', '.'),
    listOf('.', '.'),
  ))

  val s1 = fromLists(listOf(
    listOf('.', '#'),
    listOf('.', '.'),
  ))

  val s2 = fromLists(listOf(
    listOf('.', '.'),
    listOf('.', '#'),
  ))

  val s3 = fromLists(listOf(
    listOf('.', '.'),
    listOf('#', '.'),
  ))

  @Test
  fun rotateClockWise_works() {
    val t0 = Tile(1L, r0, 3)
    assertEquals(r1, t0.rotateClockWise(t0.pixels))
    val t1 = Tile(1L, r1, 3)
    assertEquals(r2, t1.rotateClockWise(t1.pixels))
    val t2 = Tile(1L, r2, 3)
    assertEquals(r3, t2.rotateClockWise(t2.pixels))
  }

  @Test
  fun flips_odd_work() {
    val t0 = Tile(1L, r0, 3)
    assertEquals(r3, t0.flipX(t0.pixels))
    assertEquals(r1, t0.flipY(t0.pixels))
  }

  @Test
  fun flips_even_work() {
    val t0 = Tile(1L, s0, 2)
    assertEquals(s3, t0.flipX(t0.pixels))
    assertEquals(s1, t0.flipY(t0.pixels))
  }

  @Test
  fun getConfigurations_works() {
    val tile = Tile(1L, r0, 3)
    assertEquals(
      setOf(r0, r1, r2, r3),
      tile.configurations.map{it.pixels}.toSet())
  }

  @Test
  fun getConfigurations_complex_works() {
    val matrix = fromLists(listOf(
      listOf('#', '.', '#', '#'),
      listOf('#', '.', '.', '.'),
      listOf('#', '#', '.', '.'),
      listOf('.', '.', '.', '#'),
    ))
    val tile = Tile(1L, matrix, 4)
    assertEquals(
      16,
      tile.configurations.size)
  }

  @Test
  fun createPicture_works() {
    val s0 = fromLists(listOf(
      listOf('.', '.', '#'),
      listOf('.', '.', '#'),
      listOf('.', '.', '#'),
    ))

    val s1 = fromLists(listOf(
      listOf('#', '.', '#'),
      listOf('#', '.', '#'),
      listOf('#', '.', '#'),
    ))

    val s2 = fromLists(listOf(
      listOf('.', '.', '#'),
      listOf('#', '.', '#'),
      listOf('#', '.', '#'),
    ))

    val s3 = fromLists(listOf(
      listOf('.', '.', '.'),
      listOf('#', '.', '.'),
      listOf('#', '.', '.'),
    ))

    val tiles = listOf(
      Tile(0L, s0, 3),
      Tile(1L, s1, 3),
      Tile(2L, s2, 3),
      Tile(3L, s3, 3))

    val pic = createPicture(tiles, 2)!!

    val tile00 = pic.tiles[Coord(0, 0)]!!
    assertEquals(0, tile00.id)
    assertEquals("0", tile00.config)

    val tile01 = pic.tiles[Coord(0, 1)]!!
    assertEquals(3, tile01.id)
    assertEquals("0fyfx", tile01.config)

    val tile10 = pic.tiles[Coord(1, 0)]!!
    assertEquals(1, tile10.id)
    assertEquals("0", tile10.config)

    val tile11 = pic.tiles[Coord(1, 1)]!!
    assertEquals(2, tile11.id)
    assertEquals("0fx", tile11.config)
  }

  @Test
  fun trimBorder_works(){
    val grid = """
      #...
      .#.#
      .#..
      #...
    """.trimIndent()
    val tileGrid = parseGrid(grid.split("\n"))
    val tile = Tile(0L, tileGrid, 4)

    val expectedResult = """
      #.
      #.
    """.trimIndent()
    val expectedGrid = parseGrid(expectedResult.split("\n"))
    val expected = Tile(0L, expectedGrid, 2)

    assertEquals(expected, tile.trimBorder())
  }
}