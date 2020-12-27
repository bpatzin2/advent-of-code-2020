package days

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day21Test {

  @Test
  fun parsing(){
    val lines = """
      mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
      trh fvjkl sbzzf mxmxvkd (contains dairy)
      sqjhc fvjkl (contains soy)
      sqjhc mxmxvkd sbzzf (contains fish)
    """.trimIndent().split("\n")
    val parsed = parseFoodDetails(lines)
    assertEquals(7, parsed.allIngredients.size)
    assertEquals(5, parsed.constraints.size)
    assertTrue(parsed.constraints.contains(
      Constraint(listOf("sqjhc", "fvjkl"), "soy")))
  }

  @Test
  fun unAllocatedIngredients(){
    val lines = """
      mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
      trh fvjkl sbzzf mxmxvkd (contains dairy)
      sqjhc fvjkl (contains soy)
      sqjhc mxmxvkd sbzzf (contains fish)
    """.trimIndent().split("\n")
    val parsed = parseFoodDetails(lines)

    val safeIngs = safeIngredients(parsed.constraints, parsed.allIngredients, parsed.allAllergens)

    assertEquals(
      setOf("kfcds", "nhms", "sbzzf", "trh"),
      safeIngs
    )

    val totalSafeIngs = safeIngs.map{ing -> parsed.ingCount[ing]!!}
    assertEquals(5, totalSafeIngs.sum())
  }

  @Test
  fun day21p1TestInput_works() {
    assertEquals(5, day21pt1("input/day21Test.txt"))
  }

  @Test
  fun day21p1_works() {
    assertEquals(2542, day21pt1())
  }

  @Test
  fun day21p2TestInput_works() {
    assertEquals("mxmxvkd,sqjhc,fvjkl", day21pt2("input/day21Test.txt"))
  }

  @Test
  fun day21p2_works() {
    assertEquals("hkflr,ctmcqjf,bfrq,srxphcm,snmxl,zvx,bd,mqvk", day21pt2())
  }
}