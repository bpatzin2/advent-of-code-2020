fun inverseCaptcha(numberList: List<Int>): Long {
  if(numberList.isEmpty()) {
    return 0
  }

  val result = numberList
    .foldRightIndexed(0L) { idx, element, sum ->
      if (isNextElementSame(numberList, element, idx)) sum + element else sum
    }

  if(numberList.last() == numberList.first()){
    return result + numberList.first()
  }

  return result
}

private fun isNextElementSame(numberList: List<Int>, element: Int, idx: Int) =
  numberList.getOrNull(idx + 1) == element