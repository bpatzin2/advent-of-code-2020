package days

import org.junit.Test
import kotlin.test.assertEquals

class Day25Test {
  @Test
  fun determineLoopSize_works() {
    assertEquals(8, determineLoopSize(5764801L, 7))
    assertEquals(11, determineLoopSize(17807724L, 7))
  }

  @Test
  fun calculateEncryptionKey_works() {
    assertEquals(14897079L, calculateEncryptionKey(5764801L, 11))
    assertEquals(14897079L, calculateEncryptionKey(17807724L, 8))
  }

  @Test
  fun day25p1TestInput_works() {
    assertEquals(14897079L, day25pt1("input/day25Test.txt"))
  }

  @Test
  fun day25p1_works() {
    assertEquals(34005, day25pt1())
  }
}