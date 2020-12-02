package passwordpolicy

data class PasswordWithPolicy(val password: String, val policy: PasswordPolicy) {
    fun isValid(): Boolean{
        val matches = allPolicyCharMatches()
        return matches.count() >= policy.min && matches.count() <= policy.max
    }

    fun isValidNew(): Boolean{
        val matches = allPolicyCharMatches()
        val oneIndexedIndices = matches
            .map(MatchResult::range)
            .map{m -> m.first + 1}.toSet()
        return oneIndexedIndices.contains(policy.min) xor oneIndexedIndices.contains(policy.max)
    }

    private fun allPolicyCharMatches(): Sequence<MatchResult> {
        val policyRegex = Regex(policy.character)
        return policyRegex.findAll(password)
    }
}

fun toPasswordWithPolicy(passwordWithPolicyStr: String): PasswordWithPolicy{
    val policyWithPassword = passwordWithPolicyStr.split(": ")
    return PasswordWithPolicy(
        policyWithPassword[1],
        toPasswordPolicy(policyWithPassword[0]))
}
