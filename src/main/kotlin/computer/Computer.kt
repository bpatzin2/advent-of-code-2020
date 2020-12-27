package computer

fun exe(program: Program): ExecutionState {
  var executionState = ExecutionState(program, 0, listOf(), 1)
  while(!executionState.alreadyProcessedNextLine() &&
    !executionState.terminationSuccessful()){
    executionState = executionState.processNextInstruction()
  }

  return executionState
}