package customs

data class CustomsDeclaration(val yesAnswers: Set<Char>)

fun fromStrings(strs: List<String>): CustomsDeclaration{
   val charSet: Set<Char> = strs.map(String::toList).flatten().toSet()
   return CustomsDeclaration(charSet)
}

// a
// ab
//
// ab
// c
fun allFromString(str: String): List<CustomsDeclaration>{
   val answerGroups: List<String> = str.split(Regex("\n{2,}"))
   val answersGrouped: List<List<String>> = answerGroups.map{g -> g.split("\n")}
   return answersGrouped.map{g -> fromStrings(g)}
}