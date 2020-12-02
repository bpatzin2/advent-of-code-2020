package passwordpolicy

data class PasswordWithPolicy(val password: String, val policy: PasswordPolicy) {
    fun isValidWithMinMaxPolicy(): Boolean{
        return policy.isValidWithMinMaxPolicy(password)
    }

    fun isValidWithXorIndexPolicy(): Boolean{
        return policy.isValidWithXorIndexPolicy(password)
    }
}

fun toPasswordWithPolicy(passwordWithPolicyStr: String): PasswordWithPolicy{
    val policyWithPassword = passwordWithPolicyStr.split(": ")
    return PasswordWithPolicy(
        policyWithPassword[1],
        toPasswordPolicy(policyWithPassword[0]))
}
