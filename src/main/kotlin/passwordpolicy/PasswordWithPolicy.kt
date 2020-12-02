package passwordpolicy

data class PasswordWithPolicy(val password: String, val policy: PasswordPolicy)

fun toPasswordWithPolicy(passwordWithPolicyStr: String): PasswordWithPolicy{
    val policyWithPassword = passwordWithPolicyStr.split(": ")
    return PasswordWithPolicy(
        policyWithPassword[1],
        toPasswordPolicy(policyWithPassword[0]))
}

fun isValid(passwordWithPolicy: PasswordWithPolicy): Boolean{
    val matches = allPolicyCharMatches(passwordWithPolicy)
    val policy = passwordWithPolicy.policy
    return matches.count() >= policy.min && matches.count() <= policy.max
}

fun isValidNew(passwordWithPolicy: PasswordWithPolicy): Boolean{
    val matches = allPolicyCharMatches(passwordWithPolicy)
    val oneIndexedIndices = matches
        .map(MatchResult::range)
        .map{m -> m.first + 1}.toSet()
    val policy = passwordWithPolicy.policy
    return oneIndexedIndices.contains(policy.min) xor oneIndexedIndices.contains(policy.max)
}

private fun allPolicyCharMatches(passwordWithPolicy: PasswordWithPolicy): Sequence<MatchResult> {
    val (password, policy) = passwordWithPolicy
    val policyRegex = Regex(policy.character)
    return policyRegex.findAll(password)
}
