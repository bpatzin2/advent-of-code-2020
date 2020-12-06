package customs

// a
// ab
//
// ab
// c
data class CustomsDeclaration(val yesAnswers: Set<Char>)

fun createDeclarationsByEveryYes(str: String): List<CustomsDeclaration>{
   val answersGrouped: List<List<String>> = answerGroup(str)
   return answersGrouped.map{g -> createByEveryYes(g)}
}

fun createDeclarationsByAnyYes(str: String): List<CustomsDeclaration>{
   val answersGrouped: List<List<String>> = answerGroup(str)
   return answersGrouped.map{g -> createByAnyYes(g)}
}

fun createByAnyYes(strs: List<String>): CustomsDeclaration{
   val charSet: Set<Char> = strs
      .map(String::toSet)
      .reduce(Set<Char>::union)

   return CustomsDeclaration(charSet)
}

fun createByEveryYes(strs: List<String>): CustomsDeclaration{
   val charSets: Set<Char> = strs
      .map(String::toSet)
      .reduce(Set<Char>::intersect)

   return CustomsDeclaration(charSets)
}

private fun answerGroup(str: String): List<List<String>> {
   val answerGroups: List<String> = str.split(Regex("\n{2,}"))
   return answerGroups.map { g -> g.split("\n") }
}