package jigsaw

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PictureAnalyzerTest {

  private val monsterImage = """
    .##               #.
    #    ##    ##    ###
    .#  #  #  #  #  #...
    """.trimIndent()

  @Test
  fun isHeadOfMonster_works() {
    val imagePixels = parseGrid(monsterImage.split("\n"))
    assertFalse(isHeadOfMonster(Coord(0, 0), imagePixels))
    assertTrue(isHeadOfMonster(Coord(18, 0), imagePixels))
    assertFalse(isHeadOfMonster(Coord(19, 0), imagePixels))
  }

  @Test
  fun coordsFromHead() {
    assertEquals(15, coordsFromHead(Coord(18, 0)).size)
  }

  @Test
  fun waterRoughness_works() {
    val testInput = """
      .#.#..#.##...#.##..#####
      ###....#.#....#..#......
      ##.##.###.#.#..######...
      ###.#####...#.#####.#..#
      ##.#....#.##.####...#.##
      ...########.#....#####.#
      ....#..#...##..#.#.###..
      .####...#..#.....#......
      #..#.##..#..###.#.##....
      #.####..#.####.#.#.###..
      ###.#.#...#.######.#..##
      #.####....##..########.#
      ##..##.#...#...#.#.#.#..
      ...#..#..#.#.##..###.###
      .#.#....#.##.#...###.##.
      ###.#...#..#.##.######..
      .#.#.###.##.##.#..#.##..
      .####.###.#...###.#..#.#
      ..#.#..#..#.#.#.####.###
      #..####...#.#.#.###.###.
      #####..#####...###....##
      #.##..#..#...#..####...#
      .#.###..##..##..####.##.
      ...###...##...#...#..###
    """.trimIndent()

    val imageAsList: List<String> = testInput.split("\n")
    assertEquals(273, waterRoughness(imageAsList))
  }
}