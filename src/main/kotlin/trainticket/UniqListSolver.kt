package trainticket

// possibilities are the values that are possible at a given index
// returns a list of strings where each index has one of the
// possibilities at that index such that the remaining elements
// are also a possibility at their index and that no element
// in the result is duplicated
fun findUniqList(possibilities: List<List<String>>): List<String>?{
  val sizes = possibilities.map{it.size}
  if(sizes.all{it == 1}) return possibilities.map{it[0]}
  if(sizes.any{it == 0}) return null

  val selectedIdx = selectNextIdx(possibilities)
  val thisFieldOptions = possibilities[selectedIdx]
  for(choice in thisFieldOptions){
    val newPossibilities = makeChoiceAtIdx(choice, selectedIdx, possibilities)
    val attempt = findUniqList(newPossibilities)
    if(attempt != null) return attempt
  }
  return null
}

private fun makeChoiceAtIdx(
  choice: String,
  selectedIdx: Int,
  possibilities: List<List<String>>
): MutableList<List<String>> {
  val newPossibilities = possibilities.toMutableList()
  for (i in newPossibilities.indices) {
    newPossibilities[i] = newPossibilities[i].filter { it != choice }
  }
  newPossibilities[selectedIdx] = listOf(choice)
  return newPossibilities
}

private fun selectNextIdx(possibilities: List<List<String>>): Int {
  return possibilities.indices
    .filter{i -> possibilities[i].size > 1 }
    .minByOrNull { i -> possibilities[i].size }!!
}