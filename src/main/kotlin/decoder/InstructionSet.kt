package decoder

data class InstructionSet(
  var mask: List<Char>,
  val instructions: List<Pair<Int, Long>>)

fun parseInput(inputLines: List<String>): List<InstructionSet> {
  val instructionSets = mutableListOf<InstructionSet>()
  var currMask: List<Char>? = null
  var currInstructions = mutableListOf<Pair<Int, Long>>()
  inputLines.forEach {
    if(it.startsWith("mask")){
      if(currMask != null) {
        instructionSets.add(InstructionSet(currMask!!, currInstructions))
      }
      currMask = it.removePrefix("mask = ").toList()
      currInstructions = mutableListOf()
    }else{
      val addrAndValue = it.split(" = ")
      val addrStr = addrAndValue[0].removePrefix("mem[").removeSuffix("]")
      val valueStr = addrAndValue[1]
      currInstructions.add(Pair(addrStr.toInt(), valueStr.toLong()))
    }
  }

  instructionSets.add(InstructionSet(currMask!!, currInstructions))
  return instructionSets
}