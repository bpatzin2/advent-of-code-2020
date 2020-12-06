package customs

import org.junit.Test
import kotlin.test.assertEquals

class TestCustomsDeclaration {

  @Test
  fun allFromString_works() {
    val batchStr =
      """
        a
        abc
        d
        
        x
        xy
        x
      """.trimIndent()
    val actualDeclarations = allFromString(batchStr)
    assertEquals(
      setOf('a', 'b', 'c', 'd'),
      actualDeclarations[0].yesAnswers)
    assertEquals(
      setOf('x', 'y'),
      actualDeclarations[1].yesAnswers)
  }
}