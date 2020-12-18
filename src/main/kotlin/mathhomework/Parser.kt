package mathhomework

fun parseExpression(expressionStr: String): Expression {
  val expression = Expression(mutableListOf())
  var i = 0
  while(i < expressionStr.length){
    when {
      expressionStr[i] == '(' -> { // Expression
        val endIdx = findEndIdx(expressionStr, i)
        val subTree = parseExpression(expressionStr.substring(i+1, endIdx-1))
        expression.add(subTree)
        i = endIdx + 1 //") N"
      }
      OPS.contains(expressionStr[i]) -> { // Operator
        expression.add(OpNode(expressionStr[i]))
        i += 2
      }
      else -> { // Long
        val start = i
        while(i < expressionStr.length && expressionStr[i] != ' ') i++
        val l = expressionStr.substring(start, i).toLong()
        expression.add(LongNode(l))
        i += 1
      }
    }
  }
  return expression
}

private fun findEndIdx(expressionStr: String, start: Int): Int {
  var pendingParens = 1
  var i = start+1
  while(pendingParens != 0){
    when(expressionStr[i]) {
      ')' -> pendingParens--
      '(' -> pendingParens++
    }
    i++
  }
  return i
}