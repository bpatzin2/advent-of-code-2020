package ferry

data class Instruction(val first: Char, val second: Int)

const val NORTH = 'N'
const val SOUTH = 'S'
const val EAST = 'E'
const val WEST = 'W'
const val LEFT = 'L'
const val RIGHT = 'R'
const val FORWARD = 'F'

fun parseInstructions(strs: List<String>): List<Instruction> {
  return strs.map{
    val op = it[0]
    val value = it.substring(1)
    Instruction(op, value.toInt())
  }
}