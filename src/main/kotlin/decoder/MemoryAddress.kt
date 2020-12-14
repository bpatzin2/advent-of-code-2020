package decoder

fun updateMemoryDecoderAndSum(instructionSets: List<InstructionSet>): Long {
  val memory = instructionSets.fold(mutableMapOf<Long, Long>()) { memory, instructionSet ->
    val rez = updateMemoryDecoder(instructionSet, memory)
    rez
  }
  return memory.values.reduce(Long::plus)
}

fun updateMemoryDecoder(
  instructionSet: InstructionSet,
  memory: MutableMap<Long, Long>): MutableMap<Long, Long> {
  instructionSet.instructions.forEach {instruction ->
    val preAddress = instruction.first
    val addresses = possibleAddresses(preAddress, instructionSet.mask)
    addresses.forEach{ addr ->
      memory[addr] = instruction.second
    }
  }
  return memory
}

fun possibleAddresses(address: Int, mask: List<Char>): Set<Long> {
  val bitStr: String = java.lang.Long.toBinaryString(address.toLong())
  val paddedAddress = bitStr
    .padStart(mask.size, '0')
    .toList()
  val addresses = applyAddressDecoder(paddedAddress, mask)
  return addresses.map{it.joinToString("").toLong(2)}.toSet()
}

fun applyAddressDecoder(address: List<Char>, decoder: List<Char>): Set<List<Char>> {
  assert(address.size == decoder.size)

  if(decoder.isEmpty()){
    return mutableSetOf(listOf())
  }

  val maskBit = decoder[0]
  val addrBit = address[0]
  val validAddrBits = when (maskBit) {
    'X' -> listOf('0', '1')
    '1' -> listOf('1')
    else -> listOf(addrBit)
  }

  val rest = applyAddressDecoder(address.drop(1), decoder.drop(1))
  val result = mutableSetOf<List<Char>>()
  for(v in validAddrBits){
    for(r in rest){
      val validMask = mutableListOf<Char>()
      validMask.add(v)
      validMask.addAll(r)
      result.add(validMask)
    }
  }
  return result
}