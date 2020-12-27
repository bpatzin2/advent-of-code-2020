package computer

data class Program(
  val instructions: Map<Int, Instruction>,
  val lastLineNumber: Int){
  fun validTerminateIdx() = lastLineNumber + 1
}

fun parse(instructions: List<String>): Program {
  val instructionList = parseInstructions(instructions)
  return createProgram(instructionList)
}

fun createProgram(instructions: List<Instruction>): Program {
  val instructionMap = instructions.mapIndexed { idx, instruction ->
    idx + 1 to instruction
  }.toMap()
  return Program(instructionMap, instructions.size)
}
