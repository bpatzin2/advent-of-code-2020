package computer

data class ExecutionState(
  val program: Program,
  val acc: Int,
  val linesProcessed: List<Int>,
  val lineToProcess: Int){

  fun terminationSuccessful(): Boolean{
    return lineToProcess == program.validTerminateIdx()
  }

  fun alreadyProcessedNextLine() = linesProcessed.contains(lineToProcess)

  fun processNextInstruction(): ExecutionState {
    val currInstruction = getInstruction(lineToProcess)
    val instructionResult = currInstruction.process(acc, lineToProcess)

    val newLinesProcessed = linesProcessed.toMutableList()
    newLinesProcessed.add(lineToProcess)

    return ExecutionState(
      program,
      instructionResult.acc,
      newLinesProcessed,
      instructionResult.nextLineNumber)
  }

  private fun getInstruction(lineNumber: Int): Instruction {
    return program.instructions[lineNumber] ?: throw RuntimeException("No instruction at line $lineNumber")
  }
}