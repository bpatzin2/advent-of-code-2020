fun inverseCaptcha(numberList: List<Int>): Long {
  return numberList.foldRightIndexed(0L) { idx, element, sum ->
    if (isNextElementSame(numberList, element, idx)) sum + element else sum
  }
}

private fun isNextElementSame(numberList: List<Int>, element: Int, idx: Int): Boolean {
  val nextIndex = if(idx == numberList.size - 1) 0 else idx + 1
  return numberList[nextIndex] == element
}
