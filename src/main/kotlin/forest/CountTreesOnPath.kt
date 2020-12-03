package forest

const val TREE = '#'

fun countTreesOnPath(
    forest: List<List<Char>>,
    pathRight: Int,
    pathDown: Int,
): Int{
  var treeCount = 0
  var xPos = 0
  for (rowNum in forest.indices step pathDown) {
    val row = forest[rowNum]
    val locInRow = row[xPos]
    if(locInRow == TREE)  {
      treeCount++
    }
    xPos = (xPos + pathRight) % row.size
  }
  return treeCount
}