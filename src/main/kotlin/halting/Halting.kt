package halting

data class Instruction(val op: String, val arg: Int)

data class Program(
  val instructions: Map<Int, Instruction>,
  val lastLineNumber: Int){

  fun validTerminateIdx() = lastLineNumber + 1
}

data class ExecutionState(
  val program: Program,
  val acc: Int,
  val linesProcessed: List<Int>,
  val nextLine: Int){

  fun didAlreadyProcess(lineNumber: Int): Boolean{
    return linesProcessed.contains(lineNumber)
  }

  fun terminationSuccessful(): Boolean{
    return nextLine == program.validTerminateIdx()
  }
}

fun exe(instructions: List<Instruction>): ExecutionState {
  val program = toProgram(instructions)
  return exe(program)
}

fun toProgram(instructions: List<Instruction>): Program {
  val instructionMap = instructions.mapIndexed { idx, instruction ->
    idx + 1 to instruction
  }.toMap()
  return Program(instructionMap, instructions.size)
}

fun exe(program: Program): ExecutionState {
  val instructions = program.instructions
  var executionState = ExecutionState(program, 0, listOf(), 1)
  while(!executionState.didAlreadyProcess(executionState.nextLine) &&
          !executionState.terminationSuccessful()){
    val currInstruction = getInstruction(executionState.nextLine, instructions)

    var nextLineNumber = executionState.nextLine + 1
    var acc = executionState.acc

    when(currInstruction.op) {
      "acc" -> acc += currInstruction.arg
      "jmp" -> {
        nextLineNumber = executionState.nextLine + currInstruction.arg
      }
    }

    val newLinesProcessed = executionState.linesProcessed.toMutableList()
    newLinesProcessed.add(executionState.nextLine)
    executionState = ExecutionState(program, acc, newLinesProcessed, nextLineNumber)
  }

  return executionState
}

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

fun fixAndRunProgram(instructions: List<Instruction>): ExecutionState? {
  val program = toProgram(instructions)
  for(lineNumber in program.instructions.keys.sorted()){
    val result = runFixedProgram(lineNumber, program)
    if(result != null && result.terminationSuccessful()) {
      return result
    }
  }
  return null
}

private fun getInstruction(lineNumber: Int, instructions: Map<Int, Instruction>): Instruction {
  return instructions[lineNumber] ?: throw RuntimeException("No instruction at line $lineNumber")
}

//    nop +0
//    acc +1
//    jmp +4
fun parseInstructions(instructionStrs: List<String>): List<Instruction> {
  return instructionStrs.map(::parseInstruction)
}

private fun parseInstruction(instructionStr: String): Instruction {
  val opAndArg = instructionStr.split(" ")
  val arg =  opAndArg[1].removePrefix("+").toInt()
  return Instruction(opAndArg[0], arg)
}