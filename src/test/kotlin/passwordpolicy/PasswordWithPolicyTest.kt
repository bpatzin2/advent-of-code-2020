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
            isValid(PasswordWithPolicy("abc",  PasswordPolicy("a", 2, 4))))
    }

    @Test
    fun isValid_falseWhenMoreThanMax() {
        assertEquals(
            false,
            isValid(PasswordWithPolicy("abcaaaa",  PasswordPolicy("a", 2, 4))))
    }

    @Test
    fun isValid_trueWhenWithinRange() {
        assertEquals(
            true,
            isValid(PasswordWithPolicy("abca",  PasswordPolicy("a", 1, 4))))
    }

    @Test
    fun isValid_trueWhenMin() {
        assertEquals(
            true,
            isValid(PasswordWithPolicy("abc",  PasswordPolicy("a", 1, 3))))
    }

    @Test
    fun isValid_trueWhenMax() {
        assertEquals(
            true,
            isValid(PasswordWithPolicy("abaca",  PasswordPolicy("a", 1, 3))))
    }

    @Test
    fun isValid_worksOnTestInput() {
        //1-3 a: abcde
        //1-3 b: cdefg
        //2-9 c: ccccccccc
        assertEquals(
            true,
            isValid(PasswordWithPolicy("abcde",  PasswordPolicy("a", 1, 3))))
        assertEquals(
            false,
            isValid(PasswordWithPolicy("cdefg",  PasswordPolicy("b", 1, 3))))
        assertEquals(
            true,
            isValid(PasswordWithPolicy("ccccccccc",  PasswordPolicy("c", 2, 9))))

    }

    @Test
    fun isValidNew_falseWhenNotAtNeitherIndex() {
        assertEquals(
            false,
            isValidNew(PasswordWithPolicy("aabba",  PasswordPolicy("a", 3, 4))))
    }

    @Test
    fun isValidNew_falseWhenAtBothIndices() {
        assertEquals(
            false,
            isValidNew(PasswordWithPolicy("abba",  PasswordPolicy("a", 1, 4))))
    }

    @Test
    fun isValidNew_trueWhenAtFirstIndex() {
        assertEquals(
            true,
            isValidNew(PasswordWithPolicy("abbb",  PasswordPolicy("a", 1, 4))))
    }

    @Test
    fun isValidNew_trueWhenAtSecondIndex() {
        assertEquals(
            true,
            isValidNew(PasswordWithPolicy("bbba",  PasswordPolicy("a", 1, 4))))
    }
}