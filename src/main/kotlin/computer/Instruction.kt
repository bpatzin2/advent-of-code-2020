package computer

interface Instruction {
  fun process(
    initialAcc: Int,
    currentLine: Int,
  ): InstructionResult
}

data class InstructionResult(val acc: Int, val nextLineNumber: Int)

data class JmpInstruction(val arg: Int): Instruction {
  override fun process(
    initialAcc: Int,
    currentLine: Int
  ): InstructionResult =
    InstructionResult(
    initialAcc,
    currentLine + arg)
}

data class AccInstruction(val arg: Int): Instruction {
  override fun process(
    initialAcc: Int,
    currentLine: Int
  ): InstructionResult =
    InstructionResult(
      initialAcc + arg,
      currentLine + 1)
}

data class NopInstruction(val arg: Int): Instruction {
  override fun process(
    initialAcc: Int,
    currentLine: Int
  ): InstructionResult =
    InstructionResult(
      initialAcc,
      currentLine + 1)
}

// nop +0
// acc +1
// jmp +4
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