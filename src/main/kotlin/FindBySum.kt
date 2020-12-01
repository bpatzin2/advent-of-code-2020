fun findPair(numberList: List<Int>, sum: Int): Pair<Int, Int>? {
    val numberCounts = numberList.groupingBy { it }.eachCount()
    for (num in numberList) {
        val expectedOther = sum - num
        val expectedCountOfOther = if(num == expectedOther) 2 else 1
        val actualOtherCount = numberCounts.getOrDefault(expectedOther, 0)
        if(actualOtherCount >= expectedCountOfOther){
            return Pair(num, expectedOther)
        }
    }
    return null
}