package trainticket

fun parseInput(fileStr: String): TrainTicketNotes {
  return TrainTicketNotes(
    parseRulesRanges(getRules(fileStr)),
    parseNearbyTickets(getNearbyTicketSection(fileStr)),
    parseMyTicket(getYourTicketSection(fileStr))
  )
}

fun getYourTicketSection(fileStr: String): String {
  val section = fileStr.split("your ticket:\n")[1]
  return section.split("\n")[0]
}

fun parseMyTicket(ticketSection: String): UnlabeledTicket {
  val ticketVals = ticketSection.split(",").map{it.toInt()}
  return UnlabeledTicket(ticketVals)
}

fun parseRulesRanges(rules: List<String>): Set<Rule>{
  return rules.map { r ->
    val nameAndRanges = r.split(": ")
    val name = nameAndRanges[0]
    val rangeStrs = nameAndRanges[1].split(" or ")
    val ranges = rangeStrs.map{
      val ints = it.split("-")
      ints[0].toInt()..ints[1].toInt()
    }.toSet()
    Rule(name, ranges)
  }.toSet()
}

fun parseNearbyTickets(tickets: List<String>): Set<List<Int>>{
  return tickets.map{
    it.split(",").map(String::toInt)
  }.toSet()
}

fun getNearbyTicketSection(fileStr: String): List<String> {
  val nearbyTicketsSection = fileStr.split("nearby tickets:\n")[1]
  return nearbyTicketsSection.split("\n")
}

fun getRules(fileStr: String): List<String> {
  val rulesSection = fileStr.split("\n\nyour ticket")[0]
  return rulesSection.split("\n")
}