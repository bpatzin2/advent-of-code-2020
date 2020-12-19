package messagerules

fun parseRules(ruleStrs: List<String>): Map<Long, Rule> {
  val rules = mutableMapOf<Long, Rule>()
  ruleStrs.forEach { ruleStr ->
    val idAndRule = ruleStr.split(": ")
    val id = idAndRule[0].toLong()
    val valStr = idAndRule[1]
    val rule = if(valStr.startsWith("\"")) CharRule(valStr[1]) else createOptionList(valStr)
    rules[id] = rule
  }
  return rules
}

fun createOptionList(vararg options: RuleOption): OptionList {
  val l = mutableListOf<RuleOption>()
  l.addAll(options)
  return OptionList(l)
}

fun createOptionList(optionsStr: String): OptionList{
  val options = optionsStr
    .split(" | ")
    .map{optionStr -> optionStr.split(" ").map{v -> v.toLong() } }
  return OptionList(options)
}