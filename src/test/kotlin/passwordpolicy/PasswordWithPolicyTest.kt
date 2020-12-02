package passwordpolicy

import org.junit.Test
import kotlin.test.assertEquals

class PasswordWithPolicyTest{
    @Test
    fun toPasswordWithPolicy_works() {
        assertEquals(
            PasswordWithPolicy("abc",  PasswordPolicy("a", 1, 3)),
            toPasswordWithPolicy("1-3 a: abc"))
    }
}