package ferry

import kotlin.math.abs

const val NORTH = 'N'
const val SOUTH = 'S'
const val EAST = 'E'
const val WEST = 'W'
const val LEFT = 'L'
const val RIGHT = 'R'
const val FORWARD = 'F'

interface FerryI {
  val nsPos: Int
  val ewPos: Int

  fun distanceFrom(otherFerry: FerryI): Int {
    return abs(nsPos - otherFerry.nsPos) +
        abs(ewPos - otherFerry.ewPos)
  }

  fun applyInstructions(instructions: List<Pair<Char, Int>>): FerryI {
    return instructions.fold(this, FerryI::applyInstruction)
  }

  fun applyInstruction(instruction: Pair<Char, Int>): FerryI{
    val (op, value) = instruction
    return when(op){
      NORTH -> applyMove(op, value)
      SOUTH -> applyMove(op, value)
      EAST -> applyMove(op, value)
      WEST -> applyMove(op, value)
      LEFT -> applyTurn(op, value)
      RIGHT -> applyTurn(op, value)
      FORWARD -> applyForward(value)
      else -> throw RuntimeException("unknown instruction type $instruction")
    }
  }

  fun applyMove(dir: Char, value: Int): FerryI
  fun applyTurn(dir: Char, turnDegrees: Int): FerryI
  fun applyForward(value: Int): FerryI
}

fun parseInput(strs: List<String>): List<Pair<Char, Int>> {
  return strs.map{
    val op = it[0]
    val value = it.substring(1)
    Pair(op, value.toInt())
  }
}