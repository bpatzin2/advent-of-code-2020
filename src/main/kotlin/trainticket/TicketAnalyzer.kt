package trainticket

fun multiplyMyDepartureFields(ticketNotes: TrainTicketNotes): Long{
  val myTicket = determineMyTicket(ticketNotes)
  val departureFields = myTicket.entries.filter { it.key.startsWith("departure") }
  return departureFields.fold(1L){acc, entry -> acc * entry.value}
}

fun determineMyTicket(ticketNotes: TrainTicketNotes): Map<String, Int>{
  val fieldOrder = determineTicketFieldOrder(ticketNotes)
  return fieldOrder.mapIndexed{ idx, field ->
    val myVal = ticketNotes.myTicket[idx]
    field to myVal
  }.toMap()
}

fun determineTicketFieldOrder(ticketNotes: TrainTicketNotes): List<String> {
  val validTickets = ticketNotes.validNearbyTickets()
  val rules = ticketNotes.rules
  val possibilities: List<Set<Rule>> = validTickets.first().mapIndexed { valueIdx, _ ->
    val allValuesAtIndex = validTickets.map { it[valueIdx] }.toSet()
    validPossibilities(allValuesAtIndex, rules)
  }

  return resolvePossibilities(possibilities)
}

private fun resolvePossibilities(possibilities: List<Set<Rule>>): List<String> {
  val possibilityNames = possibilities.map{ruleSet -> ruleSet.map{rule -> rule.field}}
  return findUniqList(possibilityNames) ?: throw RuntimeException("No results found")
}

private fun validPossibilities(values: Set<Int>, rules: Set<Rule>): Set<Rule> {
  return rules.filter { rule -> values.all(rule::isValidValue) }.toSet()
}