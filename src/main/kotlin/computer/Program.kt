package computer

data class Program(
  val instructions: Map<Int, Instruction>,
  val lastLineNumber: Int){

  fun validTerminateIdx() = lastLineNumber + 1
}