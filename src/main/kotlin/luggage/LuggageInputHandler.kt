package luggage

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap

fun parseInput(string: String): Multimap<String, BagContent> {
  val lines = string.split("\n")
  val rules: Multimap<String, BagContent> = HashMultimap.create()
  lines.forEach { line ->
    val lineRule: Rule? = createRule(line)
    if(lineRule != null){
      rules.putAll(lineRule.bag, lineRule.contents)
    }
  }
  return rules
}

fun createRule(ruleStr: String): Rule? {
  if(ruleStr.endsWith("no other bags.")){
    return null
  }
  val ruleStrTrimmed = ruleStr.removeSuffix(".")
  val bagAndContent = ruleStrTrimmed.split(" bags contain ")
  val bag = bagAndContent[0]
  val contentsStr = bagAndContent[1]
  val resultContent: MutableSet<BagContent> = mutableSetOf()
  val contents = contentsStr.split(", ")
  contents.forEach { content ->
    val trimmedContent1 = content.removeSuffix(" bags")
    val trimmedContent = trimmedContent1.removeSuffix(" bag")
    val numAndBag = trimmedContent.split(" ", limit=2)
    val num = numAndBag[0].toInt()
    resultContent.add(BagContent(numAndBag[1], num))
  }
  return Rule(bag, resultContent)
}