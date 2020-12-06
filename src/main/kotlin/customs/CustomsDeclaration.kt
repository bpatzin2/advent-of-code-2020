package customs

// a
// ab
//
// ab
// c
data class CustomsDeclaration(val yesAnswers: Set<Char>)

fun anyYesAnswer(strs: List<String>): CustomsDeclaration{
   val charSet: Set<Char> = strs.map(String::toList).flatten().toSet()
   return CustomsDeclaration(charSet)
}

fun everyYesAnswer(strs: List<String>): CustomsDeclaration{
   val charSets: List<Set<Char>> = strs.map(String::toList).map(List<Char>::toSet)
   return CustomsDeclaration(charSets.reduce{s1, s2 -> s1.intersect(s2)})
}

fun allFromStringEveryYes(str: String): List<CustomsDeclaration>{
   val answersGrouped: List<List<String>> = answerGroup(str)
   return answersGrouped.map{g -> everyYesAnswer(g)}
}

fun allFromStringAnyYes(str: String): List<CustomsDeclaration>{
   val answersGrouped: List<List<String>> = answerGroup(str)
   return answersGrouped.map{g -> anyYesAnswer(g)}
}

private fun answerGroup(str: String): List<List<String>> {
   val answerGroups: List<String> = str.split(Regex("\n{2,}"))
   return answerGroups.map { g -> g.split("\n") }
}