package computer

data class Instruction(val op: String, val arg: Int) {
  fun process(
    initialAcc: Int,
    currentLine: Int,
  ): InstructionResult {

    var nextLineNumber = currentLine + 1
    var acc = initialAcc

    when (op) {
      "acc" -> {
        acc += arg
      }
      "jmp" -> {
        nextLineNumber = currentLine + arg
      }
    }

    return InstructionResult(
      acc,
      nextLineNumber
    )
  }
}

data class InstructionResult(val acc: Int, val nextLineNumber: Int)