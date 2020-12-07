package days

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import fileinput.asString

data class Rule(val bag: String, val contents: Set<BagContent>)
data class BagContent(val bag: String, val num: Int)

data class RecursiveContent(val bag: String, val num: Int, val contents: Set<RecursiveContent>)

fun countNumContained(
  bag: String,
  rules: Multimap<String, BagContent>
): Int {
  val unpacked: Multimap<String, RecursiveContent> = unpack(rules)
  val contents: MutableCollection<RecursiveContent> = unpacked.get(bag)
  return recursiveCount(contents)
}

fun recursiveCount(contents: Collection<RecursiveContent>): Int{
  return contents.map{content -> content.num + ( content.num * recursiveCount(content.contents))}.sum()
}

fun findAllBagsThatCanContain(
  bagToFind: String,
  rules: Multimap<String, BagContent>
): Set<String> {
  val unpacked: Multimap<String, RecursiveContent> = unpack(rules)
  val result: MutableSet<String> = mutableSetOf()
  unpacked.asMap().forEach { (bag, recursiveContent) ->
    if(recursiveContains(bagToFind, recursiveContent, unpacked)){
      result.add(bag)
    }
  }
  return result
}

fun recursiveContains(
  bagToFind: String,
  unpackedRule: Collection<RecursiveContent>,
  unpackedRules: Multimap<String, RecursiveContent>
  ): Boolean {
  unpackedRule.forEach { recursiveContent ->
    if(recursiveContent.bag == bagToFind){
      return true
    }
  }
  unpackedRule.forEach { recursiveContent ->
    val newRule = unpackedRules.get(recursiveContent.bag)
    if(recursiveContains(bagToFind, newRule, unpackedRules)){
      return true
    }
  }
  return false
}

fun unpack(
  rules: Multimap<String, BagContent>,
 ): Multimap<String, RecursiveContent> {
  val result: Multimap<String, RecursiveContent> = HashMultimap.create()
  rules.keySet().forEach { ruleBag ->
    result.putAll(ruleBag, unpackBag(ruleBag, rules))
  }
  return result;
}

fun unpackBag(
  bagToUnpack: String,
  rules: Multimap<String, BagContent>,
): Set<RecursiveContent> {
  val result: MutableSet<RecursiveContent> = mutableSetOf()
  val rule = rules.get(bagToUnpack) ?: return result
  rule.forEach { contentBag ->
    result.add(
      RecursiveContent(
        contentBag.bag,
        contentBag.num,
        unpackBag(contentBag.bag, rules)))
  }
  return result
}

fun parseInput(string: String): Multimap<String, BagContent>{
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

fun day7pt1(pathname: String): Int {
  val inputStr = asString(pathname)
  val input = parseInput(inputStr)
  val result: Set<String> = findAllBagsThatCanContain("shiny gold", input)
  return result.size
}

fun day7pt1(): Int {
  return day7pt1("input/day7.txt")
}

fun day7pt2(pathname: String): Int {
  val inputStr = asString(pathname)
  val input = parseInput(inputStr)
  return countNumContained("shiny gold", input)
}

fun day7pt2(): Int {
  return day7pt2("input/day7.txt")
}

fun main() {
  println("Part 1: " + day7pt1())
  println("Part 2: " + day7pt2())
}