package passwordpolicy

data class PasswordPolicy(val character: String, val first: Int, val second: Int) {
  fun isValidWithMinMaxPolicy(password: String): Boolean {
    val matches = allPolicyCharMatches(password)
    return matches.count() in first..second
  }

  fun isValidWithXorIndexPolicy(password: String): Boolean {
    val matches = allPolicyCharMatches(password)
    val oneIndexedIndices = matches
      .map(MatchResult::range)
      .map { m -> m.first + 1 }.toSet()
    return oneIndexedIndices.contains(first) xor
        oneIndexedIndices.contains(second)
  }

  private fun allPolicyCharMatches(password: String): Sequence<MatchResult> {
    val policyRegex = Regex(character)
    return policyRegex.findAll(password)
  }
}

fun parsePasswordPolicy(passwordPolicyStr: String): PasswordPolicy {
  val policyParts = passwordPolicyStr.split(" ")
  val minAndMax = policyParts[0].split("-")
  return PasswordPolicy(policyParts[1], minAndMax[0].toInt(), minAndMax[1].toInt())
}