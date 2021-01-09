package trainticket

data class UnlabeledTicket(val vals: List<Int>)

data class TrainTicketNotes(
  val rules: Set<Rule>,
  val nearbyTickets: Set<List<Int>>,
  val myTicket: UnlabeledTicket,
) {

  fun validNearbyTickets(): Set<UnlabeledTicket> {
    val flattenedRules = rules.flatMap { rule -> rule.ranges }
    return nearbyTickets
      .filter{ ticket ->
        ticket.all{ ticketValue ->
          flattenedRules.any{rule -> rule.contains(ticketValue)}
        }
      }
      .map{UnlabeledTicket(it)}
      .toSet()
  }
}

data class Rule(val field: String, val ranges: Set<ClosedRange<Int>>){
  fun isValidValue(value: Int): Boolean{
    return ranges.any{r -> r.contains(value)}
  }
}
