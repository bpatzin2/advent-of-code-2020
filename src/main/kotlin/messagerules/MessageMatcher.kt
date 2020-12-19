package messagerules

typealias RuleOption = List<Long>
sealed class Rule {
  data class OptionList(val options: List<RuleOption>): Rule()
  data class CharRule(val c: Char): Rule()
}

typealias OptionList = Rule.OptionList
typealias CharRule = Rule.CharRule

fun matches(str: String, rules: Map<Long, Rule>): Boolean{
  return MessageMatcher(rules).matches(str)
}

data class MessageMatcher(val rules: Map<Long, Rule>) {

  fun matches(msg: String): Boolean {
    val ms = matches(msg,0)
    return ms.any { it == msg.indices.last }
  }

  // returns the last index of each match in msg
  private fun matches(msg: String, ruleNum: Long): List<Int> {
    if (msg.isEmpty()) return emptyList()
    return when (val rule = rules[ruleNum]!!) {
      is CharRule -> {
        if (msg[0] == rule.c) listOf(0) else emptyList()
      }
      is OptionList -> {
        return matchesOptionList(rule, msg)
      }
    }
  }

  private fun matchesOptionList(rule: OptionList, msg: String): List<Int> {
    return rule.options
      .asSequence()
      .map { option -> matchesOptionElements(option, msg) }
      .flatten()
      .toSet()
      .toList()
  }

  private fun matchesOptionElements(optionElements: List<Long>, msg: String): List<Int> {
    if (optionElements.size > msg.length) return emptyList()
    var matchedIndices = listOf(-1)
    for (i in optionElements.indices) {
      matchedIndices = matchesOptionElement(optionElements[i], matchedIndices, msg)
      if (matchedIndices.isEmpty()) {
        return emptyList()
      }
    }
    return matchedIndices
  }

  private fun matchesOptionElement(optionElement: Long, prevMatchedIndexes: List<Int>, msg: String): List<Int> {
    val matchedIndexes = mutableSetOf<Int>()
    for (p in prevMatchedIndexes) {
      val nextIndex = p + 1
      val remaining = msg.drop(nextIndex)
      val matches = matches(remaining, optionElement)
      matchedIndexes.addAll(matches.map { it + nextIndex })
    }
    return matchedIndexes.toList()
  }
}