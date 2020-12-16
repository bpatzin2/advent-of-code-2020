package trainticket

data class TrainTicketNotes(
  val rules: Set<Rule>,
  val nearbyTickets: Set<List<Int>>,
  val myTicket: List<Int>
) {

  fun validNearbyTickets(): Set<List<Int>>{
    val flattenedRules = rules.flatMap { rule -> rule.ranges }
    return nearbyTickets.filter{ ticket ->
      ticket.all{ticketValue ->
        flattenedRules.any{rule -> rule.contains(ticketValue)}
      }
    }.toSet()
  }

}

data class Rule(val field: String, val ranges: Set<ClosedRange<Int>>){
  fun isValidValue(value: Int): Boolean{
    return ranges.any{r -> r.contains(value)}
  }
}
