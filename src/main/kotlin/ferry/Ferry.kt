package ferry

import kotlin.math.abs

interface Ferry {
  val nsPos: Int
  val ewPos: Int

  fun distanceFrom(otherFerry: Ferry): Int {
    return abs(nsPos - otherFerry.nsPos) +
        abs(ewPos - otherFerry.ewPos)
  }

  fun applyInstructions(instructions: List<Instruction>): Ferry {
    return instructions.fold(this, Ferry::applyInstruction)
  }

  fun applyInstruction(instruction: Instruction): Ferry{
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

  fun applyMove(dir: Char, value: Int): Ferry
  fun applyTurn(dir: Char, turnDegrees: Int): Ferry
  fun applyForward(value: Int): Ferry
}