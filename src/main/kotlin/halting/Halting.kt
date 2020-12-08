package halting

import computer.ExecutionState
import computer.Instruction
import computer.Program
import computer.exe

fun fixCorruptedInstruction(idx: Int, program: Program): Program? {
  val oldInstruction = program.instructions[idx] ?: throw RuntimeException("No instruction at $idx")
  val newInstructions = program.instructions.toMutableMap()
  newInstructions[idx] = fixCorruptedInstruction(oldInstruction) ?: return null
  return Program(newInstructions, program.lastLineNumber)
}

fun fixCorruptedInstruction(instruction: Instruction): Instruction? {
  return when(instruction.op) {
    "nop" -> Instruction("jmp", instruction.arg)
    "jmp" -> Instruction("nop", instruction.arg)
    else -> null
  }
}

fun runFixedProgram(lineNumber: Int, program: Program): ExecutionState? {
  val fixedProgram = fixCorruptedInstruction(lineNumber, program) ?: return null
  return exe(fixedProgram)
}

fun fixAndRunProgram(program: Program): ExecutionState? {
  for(lineNumber in program.instructions.keys.sorted()){
    val result = runFixedProgram(lineNumber, program)
    if(result != null && result.terminationSuccessful()) {
      return result
    }
  }
  return null
}