package passwordpolicy

import org.junit.Test
import kotlin.test.assertEquals

class PasswordPolicyTest{
    @Test
    fun toPasswordPolicy_works() {
        assertEquals(
            PasswordPolicy("a", 1, 3),
            parsePasswordPolicy("1-3 a"))
    }
}