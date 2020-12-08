package computer

interface Instruction {
  fun process(
    initialAcc: Int,
    currentLine: Int,
  ): InstructionResult
}

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

data class InstructionResult(val acc: Int, val nextLineNumber: Int)