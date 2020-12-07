import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap

data class Rule(val bag: String, val contents: Set<BagContent>)
data class BagContent(val bag: String, val num: Int)

data class RecursiveContent(val bag: String, val num: Int, val contents: Set<RecursiveContent>)

fun countBagsContainedIn(
  bag: String,
  rules: Multimap<String, BagContent>
): Int {
  val unpacked: Multimap<String, RecursiveContent> = unpack(rules)
  val contents: MutableCollection<RecursiveContent> = unpacked.get(bag)
  return recursiveCount(contents)
}

fun recursiveCount(contents: Collection<RecursiveContent>): Int{
  return contents.map{content ->
    content.num + ( content.num * recursiveCount(content.contents))
  }.sum()
}

fun allBagsThatContain(
  bagToFind: String,
  rules: Multimap<String, BagContent>
): Set<String> {
  val recursiveRules: Multimap<String, RecursiveContent> = unpack(rules)
  return recursiveRules
    .keySet()
    .filter { bagToSearch ->
      recursiveContains(bagToFind, bagToSearch, recursiveRules)
    }
    .toSet()
}

fun recursiveContains(
  bagToFind: String,
  bagToSearch: String,
  unpackedRules: Multimap<String, RecursiveContent>
): Boolean {
  val contents = unpackedRules.get(bagToSearch)
  return contents.any { content ->
    content.bag == bagToFind ||
      recursiveContains(bagToFind, content.bag, unpackedRules)
  }
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
  bag: String,
  rules: Multimap<String, BagContent>,
): Set<RecursiveContent> {
  val rule = rules.get(bag) ?: return setOf()
  return rule.map { contentBag ->
    RecursiveContent(
      contentBag.bag,
      contentBag.num,
      unpackBag(contentBag.bag, rules))
  }.toSet()
}

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