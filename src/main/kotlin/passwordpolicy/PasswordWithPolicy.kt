package passwordpolicy

data class PasswordWithPolicy(val password: String, val policy: PasswordPolicy)

fun toPasswordWithPolicy(passwordWithPolicyStr: String): PasswordWithPolicy{
    val policyWithPassword = passwordWithPolicyStr.split(": ")
    return PasswordWithPolicy(
        policyWithPassword[1],
        toPasswordPolicy(policyWithPassword[0]))
}
