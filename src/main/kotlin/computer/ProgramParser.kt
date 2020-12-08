package computer

fun toProgram(instructions: List<Instruction>): Program {
  val instructionMap = instructions.mapIndexed { idx, instruction ->
    idx + 1 to instruction
  }.toMap()
  return Program(instructionMap, instructions.size)
}

//    nop +0
//    acc +1
//    jmp +4
fun parseInstructions(instructionStrs: List<String>): List<Instruction> {
  return instructionStrs.map(::parseInstruction)
}

fun parseInstruction(instructionStr: String): Instruction {
  val opAndArg = instructionStr.split(" ")
  val arg =  opAndArg[1].removePrefix("+").toInt()
  return Instruction(opAndArg[0], arg)
}