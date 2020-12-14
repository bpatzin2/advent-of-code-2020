package decoder

fun updateMemoryBitMaskedAndSum(instructionSets: List<InstructionSet>): Long {
  val memory = updateMemoryBitMasked(instructionSets)
  return memory.values.reduce(Long::plus)
}

private fun updateMemoryBitMasked(instructionSets: List<InstructionSet>): MutableMap<Int, Long> {
  return instructionSets.fold(mutableMapOf()) { memory, instructionSet ->
    updateMemoryBitMasked(instructionSet, memory)
  }
}

fun updateMemoryBitMasked(
  instructionSet: InstructionSet,
  memory: MutableMap<Int, Long>): MutableMap<Int, Long> {

  return instructionSet.instructions.fold(memory){mem, inst ->
    mem[inst.first] = applyMask(inst.second, instructionSet.mask)
    mem
  }
}

fun applyMask(value: Long, mask: List<Char>): Long {
  val bitStr: String = java.lang.Long.toBinaryString(value)
  val paddedValue = bitStr.padStart(mask.size, '0')
  val maskedBits = applyMaskToPadded(paddedValue, mask)
  return maskedBits.joinToString("").toLong(2)
}

fun applyMaskToPadded(
  paddedValue: String,
  mask: List<Char>
): MutableList<Char> {
  val result = paddedValue.toMutableList()
  mask.forEachIndexed { idx, maskBit ->
    when (maskBit) {
      '0' -> result[idx] = '0'
      '1' -> result[idx] = '1'
    }
  }
  return result
}