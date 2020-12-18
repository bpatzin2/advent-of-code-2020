package conway

import org.junit.Test
import kotlin.test.assertEquals

class ConwayNDimensionTest {
  @Test
  fun nextState_works() {
    val startingState = """
      .#.
      ..#
      ###
    """.trimIndent().split("\n")
    val initialState = parse(startingState, 3)
    val nextState = nextState(initialState)
    assertEquals(11, nextState.size)
  }
}