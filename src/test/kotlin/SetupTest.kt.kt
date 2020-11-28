package test
import run
import org.junit.Test
import kotlin.test.assertEquals


class SetupTest {
    @Test
    fun whenRun_thenAnswerIs4() {
        assertEquals(4, run())
    }
}