package mathhomework

fun addAdditionPrecedence(inExp: Expression): Expression{
  val result = addAdditionPrecedenceRecur(inExp)
  if(result !is Expression) throw java.lang.RuntimeException("invalid $result")
  return result
}

private fun addAdditionPrecedenceRecur(inExp: Node): Node{
  if(inExp !is Expression) return inExp
  val resolvedNodes = inExp.nodes.map{addAdditionPrecedenceRecur(it)}
  return resolveFlatNodes(resolvedNodes)
}

// "flat" as in you don't need to navigate into the node
// - just organizing precedence at this fixed level
private fun resolveFlatNodes(flatNodes: List<Node>): Expression {
  var currNodes = flatNodes.toMutableList()
  var i = 1
  while (i < currNodes.size - 1) {
    val operator = currNodes[i]
    if (operator !is OpNode) throw RuntimeException("Operator $operator")
    if (operator.op == '+') {
      val currNode = currNodes[i - 1]
      val otherNode = currNodes[i + 1]
      val newNodes = if (i == 1) mutableListOf() else currNodes.take(i - 1).toMutableList()
      newNodes.add(createExpression(currNode, operator, otherNode))
      newNodes.addAll(currNodes.drop(i + 2))
      currNodes = newNodes
      continue
    }
    i += 2
  }
  return Expression(currNodes)
}