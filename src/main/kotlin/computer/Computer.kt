package computer

fun exe(instructions: List<Instruction>): ExecutionState {
  val program = toProgram(instructions)
  return exe(program)
}

fun exe(program: Program): ExecutionState {
  var executionState = ExecutionState(program, 0, listOf(), 1)
  while(!executionState.didAlreadyProcess(executionState.lineToProcess) &&
    !executionState.terminationSuccessful()){
    executionState = executionState.processNextInstruction()
  }

  return executionState
}