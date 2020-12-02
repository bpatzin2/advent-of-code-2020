package passwordpolicy

data class PasswordWithPolicy(val password: String, val policy: PasswordPolicy)

fun toPasswordWithPolicy(passwordWithPolicyStr: String): PasswordWithPolicy{
    val policyWithPassword = passwordWithPolicyStr.split(": ")
    return PasswordWithPolicy(
        policyWithPassword[1],
        toPasswordPolicy(policyWithPassword[0]))
}

fun isValid(passwordWithPolicy: PasswordWithPolicy): Boolean{
    val policy = passwordWithPolicy.policy
    val password = passwordWithPolicy.password
    val policyRegex = Regex(policy.character)
    val matches = policyRegex.findAll(password)
    return matches.count() >= policy.min && matches.count() <= policy.max
}

fun isValidNew(passwordWithPolicy: PasswordWithPolicy): Boolean{
    val policy = passwordWithPolicy.policy
    val password = passwordWithPolicy.password
    val policyRegex = Regex(policy.character)
    val matches = policyRegex.findAll(password)
    val matchRanges = matches.map(MatchResult::range)
    val oneIndexedIndices = matchRanges.map{m -> m.first + 1}.toSet()
    return oneIndexedIndices.contains(policy.min) xor oneIndexedIndices.contains(policy.max)
}
