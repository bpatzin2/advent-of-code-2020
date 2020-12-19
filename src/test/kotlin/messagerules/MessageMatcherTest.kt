package messagerules

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MessageMatcherTest {
  @Test
  fun simpleMatches_works(){
    val rules = mapOf(0L to CharRule('a'))
    assertTrue(matches("a", rules))
    assertFalse(matches("b", rules))
    assertFalse(matches("ab", rules))
    assertFalse(matches("ba", rules))
  }

  @Test
  fun soloOption_works(){
    // 0: 1
    // 1: "a"
    val rules = mapOf(
      0L to createOptionList(listOf(1L)),
      1L to CharRule('a'),
    )
    assertTrue(matches("a", rules))
    assertFalse(matches("ba", rules))
    assertFalse(matches("bc", rules))
  }

  @Test
  fun soloOption2_works(){
    // 0: 1 2
    // 1: "a"
    // 2: "b"
    val rules = mapOf(
      0L to createOptionList(listOf(1L, 2L)),
      1L to CharRule('a'),
      2L to CharRule('b')
    )
    assertTrue(matches("ab", rules))
    assertFalse(matches("ba", rules))
    assertFalse(matches("xab", rules))
    assertFalse(matches("abx", rules))
  }

  @Test
  fun twoSoloOptions_works(){
    // 0: 1 2 | 3
    // 1: "a"
    // 2: "b"
    // 3: "c"
    val rules = mapOf(
      0L to createOptionList(listOf(1L, 2L), listOf(3L)),
      1L to CharRule('a'),
      2L to CharRule('b'),
      3L to CharRule('c')
    )
    assertTrue(matches("ab", rules))
    assertTrue(matches("c", rules))
    assertFalse(matches("xc", rules))
    assertFalse(matches("a", rules))
    assertFalse(matches("bb", rules))
  }

  @Test
  fun singleComplex_works(){
    // 0: 4 1
    // 1: "a"
    // 3: "c"
    // 4: 3 3
    val rules = mapOf(
      0L to createOptionList(listOf(4L, 1L)),
      1L to CharRule('a'),
      3L to CharRule('c'),
      4L to createOptionList(listOf(3L, 3L)),
    )
    assertTrue(matches("cca", rules))
    assertFalse(matches("ccaa", rules))
    assertFalse(matches("aca", rules))
    assertFalse(matches("a", rules))
  }

  @Test
  fun complex_works(){
    // 0: 3
    // 1: "a"
    // 2: "b"
    // 3: 4 5 | 5 4
    // 4: 1 1
    // 5: 2 2
    val rules = mapOf(
      0L to createOptionList(listOf(3L)),
      1L to CharRule('a'),
      2L to CharRule('b'),
      3L to createOptionList(listOf(4L, 5L), listOf(5L, 4L)),
      4L to createOptionList(listOf(1L, 1L)),
      5L to createOptionList(listOf(2L, 2L)),
    )
    assertTrue(matches("aabb", rules))
    assertTrue(matches("bbaa", rules))
    assertFalse(matches("bbbbaaaa", rules))
    assertFalse(matches("abab", rules))
    assertFalse(matches("abba", rules))
    assertFalse(matches("aabxb", rules))
  }

  @Test
  fun testInput_works(){
    // 0: 4 1 5
    // 1: 2 3 | 3 2
    // 2: 4 4 | 5 5
    // 3: 4 5 | 5 4
    // 4: "a"
    // 5: "b"

    val rules = mapOf(
      0L to createOptionList(listOf(4L, 1L, 5L)),
      1L to createOptionList(listOf(2L, 3L), listOf(3L, 2L)),
      2L to createOptionList(listOf(4L, 4L), listOf(5L, 5L)),
      3L to createOptionList(listOf(4L, 5L), listOf(5L, 4L)),
      4L to CharRule('a'),
      5L to CharRule('b'),
    )
    assertTrue(matches("ababbb", rules))
    assertFalse(matches("bababa", rules))
    assertTrue(matches("abbbab", rules))
    assertFalse(matches("aaabbb", rules))
    assertFalse(matches("aaaabbb", rules))
  }
}