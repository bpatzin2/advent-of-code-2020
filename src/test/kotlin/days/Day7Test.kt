package days

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import org.junit.Test
import kotlin.test.assertEquals

class Day7Test {

  @Test
  fun bagsWhichCanRecursivelyContain_falseWhenNoRules() {
    val rules: Multimap<String, BagContent> = HashMultimap.create()
    val result = findAllBagsThatCanContain("light red", rules)
    assertEquals(setOf(), result)
  }

  @Test
  fun findAllBagsThatCanContain_whenOneMatchingRule() {
    val rules: Multimap<String, BagContent> = HashMultimap.create()
    rules.put("light red", BagContent("bright white", 1))
    val result: Set<String> = findAllBagsThatCanContain("bright white", rules)
    assertEquals(setOf("light red"), result)
  }

  @Test
  fun findAllBagsThatCanContain_matchingRecursiveRule() {
    val rules: Multimap<String, BagContent> = HashMultimap.create()
    rules.put("light red", BagContent("bright white", 1))
    rules.put("bright white", BagContent("shiny gold", 1))
    val result: Set<String> = findAllBagsThatCanContain("shiny gold", rules)
    assertEquals(setOf("light red", "bright white"), result)
  }

  @Test
  fun findAllBagsThatCanContain_worksForTestData() {
    val result: Set<String> = findAllBagsThatCanContain("shiny gold", testInputRules())
    assertEquals(setOf("light red", "bright white", "muted yellow", "dark orange"), result)
  }

  fun testInputRules(): Multimap<String, BagContent> {
    val rules: Multimap<String, BagContent> = HashMultimap.create()
    rules.putAll("light red", listOf(
      BagContent("bright white", 1),
      BagContent("muted yellow", 2),
    ))
    rules.putAll("dark orange", listOf(
      BagContent("bright white", 3),
      BagContent("muted yellow", 4),
    ))
    rules.putAll("bright white", listOf(
      BagContent("shiny gold", 1),
    ))
    rules.putAll("muted yellow", listOf(
      BagContent("shiny gold", 2),
      BagContent("faded blue", 9),
    ))
    rules.putAll("shiny gold", listOf(
      BagContent("dark olive", 1),
      BagContent("vibrant plum", 2),
    ))
    rules.putAll("dark olive", listOf(
      BagContent("faded blue", 3),
      BagContent("dotted black", 4),
    ))
    rules.putAll("vibrant plum", listOf(
      BagContent("faded blue", 5),
      BagContent("dotted black", 6),
    ))
    return rules
  }

  val testInput = """
    light red bags contain 1 bright white bag, 2 muted yellow bags.
    dark orange bags contain 3 bright white bags, 4 muted yellow bags.
    bright white bags contain 1 shiny gold bag.
    muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
    shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
    dark olive bags contain 3 faded blue bags, 4 dotted black bags.
    vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
    faded blue bags contain no other bags.
    dotted black bags contain no other bags.
    """.trimIndent()

  @Test
  fun testRuleParsing() {
    assertEquals(testInputRules(), parseInput(testInput))
  }

  @Test
  fun testStrInput() {
    val input = parseInput(testInput)
    val result: Set<String> = findAllBagsThatCanContain("shiny gold", input)
    assertEquals(4, result.size)
  }

  @Test
  fun day7p1TestInput_works() {
    assertEquals(4, day7pt1("input/day7Test.txt"))
  }

  @Test
  fun day7p1_works() {
    assertEquals(197, day7pt1())
  }

  @Test
  fun day7p2TestInput_works() {
    assertEquals(32, day7pt2("input/day7Test.txt"))
  }

  @Test
  fun day7p2_works() {
    assertEquals(85324, day7pt2())
  }
}