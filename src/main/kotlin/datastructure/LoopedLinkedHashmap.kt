package datastructure

data class LoopedLinkedHashmap
constructor(private val nodes: Set<Node>, var currNode: Node) {
  val max = nodes.size
  private val lookup: Map<Int, Node> = nodes.map{ node -> node.int to node}.toMap()

  fun find(value: Int): Node {
    return lookup[value]!!
  }

  fun addAllAfter(node: Node, nodesToAdd: Node) {
    val right = node.next!!
    node.next = nodesToAdd
    nodesToAdd.prev = node

    var end = nodesToAdd
    while(end.next != null){
      end = end.next!!
    }

    end.next = right
    right.prev = end
  }

  fun removeNextN(n: Int): Node {
    val first = currNode.next!!

    var last = first
    for(i in 1 until n)
      last = last.next!!

    val right = last.next!!

    currNode.next = right
    right.prev = currNode

    last.next = null
    return first
  }

  fun toList(): List<Int>{
    val result = mutableListOf<Int>()
    var node = currNode
    for(i in 0 until max){
      result.add(node.int)
      node = node.next!!
    }
    return result
  }

  fun currValue(): Int {
    return currNode.int
  }
}

data class Node(val int: Int){
  var prev: Node? = null
  var next: Node? = null
}

fun nodeList(node: Node): List<Int> {
  val result = mutableListOf<Int>()
  var currNode = node
  while(true){
    result.add(currNode.int)
    if(currNode.next != null) {
      currNode = currNode.next!!
    }else{
      break
    }
  }
  return result
}

fun buildLoopedLinkedHashmap(list: List<Int>, index: Int = 0): LoopedLinkedHashmap{
  val result = mutableListOf<Node>()
  var prev: Node? = null
  for(i in list){
    val node = Node(i)
    node.prev = prev
    if(prev != null) prev.next = node
    result.add(node)
    prev = node
  }
  val first = result[0]
  val last = result.last()
  first.prev = last
  last.next = first

  return LoopedLinkedHashmap(result.toSet(), result[index])
}
