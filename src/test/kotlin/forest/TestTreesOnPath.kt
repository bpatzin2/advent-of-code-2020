package forest

import org.junit.Test
import kotlin.test.assertEquals

class TestTreesOnPath {

  @Test
  fun countTreesOnPath_emptyForestStraightDown() {
    val emptyForest = listOf(
      listOf('.', '.'),
      listOf('.', '.')
    )
    assertEquals(0,
      countTreesOnPath(emptyForest, 0, 1))
  }

  @Test
  fun countTreesOnPath_populatedForestStraightDown() {
    val forest = listOf(
      listOf('.', '.'),
      listOf('#', '.')
    )
    assertEquals(1,
      countTreesOnPath(forest, 0, 1))
  }

  @Test
  fun countTreesOnPath_populatedForestDiagonalDown() {
    val forest = listOf(
      listOf('.', '.', '.'),
      listOf('.', '#', '.'),
      listOf('.', '.', '#'),
    )
    assertEquals(2,
      countTreesOnPath(forest, 1, 1))
  }

  @Test
  fun countTreesOnPath_forestRepeats() {
    val forest = listOf(
      listOf('.', '#', '#', '#'),
      listOf('.', '.', '.', '.'),
      listOf('.', '.', '#', '.'),
    )
    assertEquals(1,
      countTreesOnPath(forest, 3, 1))
  }

}