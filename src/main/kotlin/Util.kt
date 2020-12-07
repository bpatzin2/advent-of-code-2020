fun groupByEmptyLine(str: String): List<List<String>> {
  val answerGroups: List<String> = splitByEmptyLine(str)
  return answerGroups.map { g -> g.split("\n") }
}

fun splitByEmptyLine(str: String): List<String> {
  return str.split(Regex("\n{2,}"))
}