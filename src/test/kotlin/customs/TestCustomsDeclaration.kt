package customs

import org.junit.Test
import kotlin.test.assertEquals

class TestCustomsDeclaration {

  @Test
  fun allFromStringAnyYes_works() {
    val batchStr =
      """
        a
        abc
        d
        
        x
        xy
        x
      """.trimIndent()
    val actualDeclarations = allFromStringAnyYes(batchStr)
    assertEquals(
      setOf('a', 'b', 'c', 'd'),
      actualDeclarations[0].yesAnswers)
    assertEquals(
      setOf('x', 'y'),
      actualDeclarations[1].yesAnswers)
  }

  @Test
  fun allFromStringEveryYes_works() {
    val batchStr =
      """
        ac
        abc
        cd
        
        xy
        xy
        axy
      """.trimIndent()
    val actualDeclarations = allFromStringEveryYes(batchStr)
    assertEquals(
      setOf('c'),
      actualDeclarations[0].yesAnswers)
    assertEquals(
      setOf('x', 'y'),
      actualDeclarations[1].yesAnswers)
  }
}