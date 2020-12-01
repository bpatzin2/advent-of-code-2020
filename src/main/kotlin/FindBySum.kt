// 0(N^2) implementation to find triplet that sums to given value
fun findTriplet(numberList: List<Int>, sum: Int): List<Int>? {
    val numberCounts = numberList.groupingBy { it }.eachCount()
    for (num in numberList) {
        val updatedCounts = numberCounts.toMutableMap()
        val currCount = updatedCounts[num]!!
        updatedCounts[num] = currCount-1
        val updatedSum = sum - num
        val pair = findPair(updatedSum, updatedCounts)
        if(pair != null){
            return listOf(num, pair.first, pair.second)
        }
    }
    return null
}

fun findPair(numberList: List<Int>, sum: Int): Pair<Int, Int>? {
    val numberCounts = numberList.groupingBy { it }.eachCount()
    return findPair(sum, numberCounts)
}

// 0(N) implementation to find pair that sums to given value
private fun findPair(
    sum: Int,
    numberCounts: Map<Int, Int>,
): Pair<Int, Int>? {
    val numList = numberCounts.keys.filter { key -> numberCounts[key]!! > 0 }
    for (num in numList) {
        val expectedOther = sum - num
        val expectedCountOfOther = if (num == expectedOther) 2 else 1
        val actualOtherCount = numberCounts.getOrDefault(expectedOther, 0)
        if (actualOtherCount >= expectedCountOfOther) {
            return Pair(num, expectedOther)
        }
    }
    return null
}