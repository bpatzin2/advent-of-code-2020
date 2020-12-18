package mathhomework

fun evalAndSum(expressions: Collection<String>): Long{
  return expressions
    .map{eval(it)}
    .reduce(Long::plus)
}

fun eval(expression: String): Long{
  return parseExpression(expression).eval()
}

fun evalWithAdditionPrecedenceAndSum(expressions: Collection<String>): Long{
  return expressions
    .map{evalWithAdditionPrecedence(it)}
    .reduce(Long::plus)
}

fun evalWithAdditionPrecedence(expression: String): Long{
  return addAdditionPrecedence(parseExpression(expression)).eval()
}
