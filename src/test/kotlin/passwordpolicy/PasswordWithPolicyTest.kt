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

    @Test
    fun isValid_falseWhenLessThanMin() {
        assertEquals(
            false,
            PasswordWithPolicy("abc",  PasswordPolicy("a", 2, 4)).isValid()
        )
    }

    @Test
    fun isValid_falseWhenMoreThanMax() {
        assertEquals(
            false,
            PasswordWithPolicy("abcaaaa",  PasswordPolicy("a", 2, 4)).isValid())
    }

    @Test
    fun isValid_trueWhenWithinRange() {
        assertEquals(
            true,
            PasswordWithPolicy("abca",  PasswordPolicy("a", 1, 4)).isValid())
    }

    @Test
    fun isValid_trueWhenMin() {
        assertEquals(
            true,
            PasswordWithPolicy("abc",  PasswordPolicy("a", 1, 3)).isValid())
    }

    @Test
    fun isValid_trueWhenMax() {
        assertEquals(
            true,
            PasswordWithPolicy("abaca",  PasswordPolicy("a", 1, 3)).isValid())
    }

    @Test
    fun isValid_worksOnTestInput() {
        //1-3 a: abcde
        //1-3 b: cdefg
        //2-9 c: ccccccccc
        assertEquals(
            true,
            PasswordWithPolicy("abcde",  PasswordPolicy("a", 1, 3)).isValid())
        assertEquals(
            false,
            PasswordWithPolicy("cdefg",  PasswordPolicy("b", 1, 3)).isValid())
        assertEquals(
            true,
            PasswordWithPolicy("ccccccccc",  PasswordPolicy("c", 2, 9)).isValid())

    }

    @Test
    fun isValidNew_falseWhenNotAtNeitherIndex() {
        assertEquals(
            false,
            PasswordWithPolicy("aabba",  PasswordPolicy("a", 3, 4)).isValidNew())
    }

    @Test
    fun isValidNew_falseWhenAtBothIndices() {
        assertEquals(
            false,
            PasswordWithPolicy("abba",  PasswordPolicy("a", 1, 4)).isValidNew())
    }

    @Test
    fun isValidNew_trueWhenAtFirstIndex() {
        assertEquals(
            true,
            PasswordWithPolicy("abbb",  PasswordPolicy("a", 1, 4)).isValidNew())
    }

    @Test
    fun isValidNew_trueWhenAtSecondIndex() {
        assertEquals(
            true,
            PasswordWithPolicy("bbba",  PasswordPolicy("a", 1, 4)).isValidNew())
    }
}