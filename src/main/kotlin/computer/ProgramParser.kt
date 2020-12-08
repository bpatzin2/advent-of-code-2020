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
  val op = opAndArg[0]
  val arg =  opAndArg[1].removePrefix("+").toInt()
  return when (op) {
    "acc" -> AccInstruction(arg)
    "jmp" -> JmpInstruction(arg)
    "nop" -> NopInstruction(arg)
    else -> throw RuntimeException("unknown op $op")
  }
}