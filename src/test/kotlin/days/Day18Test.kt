package days

import mathhomework.*
import org.junit.Test
import kotlin.test.assertEquals

class Day18Test {
  @Test
  fun trivialParsing_works() {
    val str = "1 + 2"
    val expected = createExpression(
      LongNode(1L),
      OpNode('+'),
      LongNode(2L),
    )
    assertEquals(expected, parseExpression(str))
  }

  @Test
  fun simpleParenParsing_works() {
    val str = "1 + (2 + 6)"
    val expected = createExpression(
      LongNode(1L),
      OpNode('+'),
      createExpression(
        LongNode(2L),
        OpNode('+'),
        LongNode(6L),
      )
    )
    assertEquals(expected, parseExpression(str))
  }

  @Test
  fun complexParsing_works() {
    val str = "1 + (2 + (3 + 4) + (5 + 6))"
    val expected = createExpression(
      LongNode(1L),
      OpNode('+'),
      Expression(mutableListOf(
        LongNode(2L),
        OpNode('+'),
        Expression(mutableListOf(
          LongNode(3L),
          OpNode('+'),
          LongNode(4L))),
        OpNode('+'),
        Expression(mutableListOf(
          LongNode(5L),
          OpNode('+'),
          LongNode(6L))),
      ))
    )
    assertEquals(expected, parseExpression(str))
  }

  @Test
  fun trivialEval_works() {
    val expression = createExpression(
      LongNode(1L),
      OpNode('+'),
      LongNode(2L),
    )
    assertEquals(3L, expression.eval())
  }

  @Test
  fun complexEval_works() {
//    val str = "1 + (2 + (3 + 4) + (5 + 6))"
    val expression = createExpression(
      LongNode(1L),
      OpNode('+'),
      Expression(mutableListOf(
        LongNode(2L),
        OpNode('+'),
        Expression(mutableListOf(
          LongNode(3L),
          OpNode('+'),
          LongNode(4L))),
        OpNode('+'),
        Expression(mutableListOf(
          LongNode(5L),
          OpNode('+'),
          LongNode(6L))),
      ))
    )
    assertEquals(21L, expression.eval())
  }

  @Test
  fun toAddFirstExp_works() {
    val str = "1 + (2 * (3 + 4 * 5 + 6))"
    val expression = createExpression(
      createExpression(
      LongNode(1L),
      OpNode('+'),
        createExpression(
        LongNode(2L),
        OpNode('*'),
        createExpression(
          createExpression(
            LongNode(3L),
            OpNode('+'),
            LongNode(4L)),
          OpNode('*'),
          createExpression(
            LongNode(5L),
            OpNode('+'),
            LongNode(6L))))))
    assertEquals(expression, addAdditionPrecedence(parseExpression(str)))
  }

  @Test
  fun toAddFirstExpEval_works() {
    val str = "1 + (2 * (3 + 4 * 5 + 6))"
    val expWithPrec = addAdditionPrecedence(parseExpression(str))
    assertEquals(155L, expWithPrec.eval())
  }

  @Test
  fun day18p1TestInput_works() {
    assertEquals(86, day18pt1("input/day18Test.txt"))
  }

  @Test
  fun day18p1_works() {
    assertEquals(86311597203806, day18pt1())
  }

  @Test
  fun day18p2TestInput_works() {
    assertEquals(670602, day18pt2("input/day18Test2.txt"))
  }

  @Test
  fun day18p2_works() {
    assertEquals(276894767062189, day18pt2())
  }
}