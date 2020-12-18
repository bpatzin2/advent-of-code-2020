package mathhomework

interface Node

data class OpNode(val op: Char): Node
val OPS = setOf('*', '+')

interface OperandNode: Node {
  fun eval(): Long
}

data class LongNode(val l: Long): OperandNode {
  override fun eval(): Long = l
}

data class Expression(val nodes: MutableList<Node>): OperandNode{
  fun add(node: Node){
    nodes.add(node)
  }

  override fun eval(): Long {
    var currNode = nodes.first()
    if(nodes.size == 1) {
      if (currNode !is OperandNode) throw RuntimeException("currNode $currNode")
      return currNode.eval()
    }
    for (i in 1..nodes.indices.last step 2) {
      val operator = nodes[i]
      val otherNode = nodes[i + 1]
      if (operator !is OpNode) throw RuntimeException("Operator $operator")
      if (currNode !is OperandNode) throw RuntimeException("currNode $currNode")
      if (otherNode !is OperandNode) throw RuntimeException("otherNode $otherNode")
      currNode = when (operator.op) {
        '+' -> LongNode(currNode.eval() + otherNode.eval())
        '*' -> LongNode(currNode.eval() * otherNode.eval())
        else -> throw RuntimeException("Operator ${operator.op}")
      }
    }
    if (currNode !is LongNode) throw RuntimeException("currNode $currNode")
    return currNode.l
  }
}

fun createExpression(vararg nodes: Node): Expression {
  val l = mutableListOf<Node>()
  l.addAll(nodes)
  return Expression(l)
}





