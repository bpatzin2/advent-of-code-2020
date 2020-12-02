package passwordpolicy

data class PasswordPolicy(val character: String, val min: Int, val max: Int)

fun toPasswordPolicy(passwordPolicyStr: String): PasswordPolicy {
    println("BP: ")
    println(passwordPolicyStr)
    val policyParts = passwordPolicyStr.split(" ")
    val minAndMax = policyParts[0].split("-")
    return PasswordPolicy(policyParts[1], minAndMax[0].toInt(), minAndMax[1].toInt())
}