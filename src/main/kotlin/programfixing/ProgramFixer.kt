package programfixing

import computer.*

fun fixAndRunProgram(program: Program): ExecutionState? {
  for(lineNumber in program.instructions.keys.sorted()){
    val result = runFixedProgram(lineNumber, program)
    if(result != null && result.terminationSuccessful()) {
      return result
    }
  }
  return null
}

fun runFixedProgram(lineNumber: Int, program: Program): ExecutionState? {
  val fixedProgram = fixCorruptedInstruction(lineNumber, program) ?: return null
  return exe(fixedProgram)
}

private fun fixCorruptedInstruction(idx: Int, program: Program): Program? {
  val oldInstruction = program.instructions[idx] ?: throw RuntimeException("No instruction at $idx")
  val newInstructions = program.instructions.toMutableMap()
  newInstructions[idx] = fixCorruptedInstruction(oldInstruction) ?: return null
  return Program(newInstructions, program.lastLineNumber)
}

private fun fixCorruptedInstruction(instruction: Instruction): Instruction? {
  return when(instruction) {
    is NopInstruction -> JmpInstruction(instruction.arg)
    is JmpInstruction -> NopInstruction(instruction.arg)
    else -> null
  }
}