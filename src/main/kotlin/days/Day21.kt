package days

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Sets
import java.io.File

typealias Ingredient = String
typealias Allergen = String

data class Input21(
  val allIngredients: Set<Ingredient>,
  val allAllergens: Set<Allergen>,
  val constraints: List<Constraint>,
  val ingCount: Map<Ingredient, Int>,
)

fun parseFoodDetails(lines: List<String>): Input21{
  val allergenToIngredients: ArrayListMultimap<Allergen, Ingredient> = ArrayListMultimap.create()
  val allAllergens = mutableSetOf<Allergen>()
  val allIngredients = mutableSetOf<Ingredient>()
  val constraints = mutableListOf<Constraint>()
  val ingredientCount = mutableMapOf<Ingredient, Int>()
  lines.map { line ->
    val split = line.removeSuffix(")").split(" (contains ")
    val ingredients = split[0].split(" ")
    val allergens = split[1].split(", ")
    allAllergens.addAll(allergens)
    allIngredients.addAll(ingredients)
    for(allergen in allergens){
      constraints.add(Constraint(ingredients, allergen))
      allergenToIngredients.putAll(allergen, ingredients)
    }
    for(ingredient in ingredients){
      val prevCount = ingredientCount.getOrDefault(ingredient, 0)
      ingredientCount[ingredient] = prevCount + 1
    }
  }
  return Input21(allIngredients, allAllergens, constraints, ingredientCount)
}

data class Constraint(
  val ingredients: List<Ingredient>,
  val allergen: Allergen,
)

fun safeIngredients(
  constraints: List<Constraint>,
  allIngredients: Set<Ingredient>,
  allAllergens: Set<Allergen>,
): Set<Ingredient> {
  val knownResult = knownIngredients(constraints, allAllergens)
  val suspected = knownResult.unresolved.flatMap { c -> c.ingredients }
  val knownOrSuspectedHarmful = knownResult.known.values.toMutableSet()
  knownOrSuspectedHarmful.addAll(suspected)
  return Sets.difference(allIngredients, knownOrSuspectedHarmful)
}

data class Result(val known: Map<Allergen, Ingredient>, val unresolved: List<Constraint>)

private fun knownIngredients(
  allConstraints: List<Constraint>,
  allAllergens: Set<Allergen>
): Result {
  var constraints = allConstraints.toList()
  val known: MutableMap<Allergen, Ingredient> = mutableMapOf()
  var prevKnown = -1
  while(prevKnown < known.size){
    prevKnown = known.size
    val allergens = allAllergens.toMutableSet()
    allergens.removeAll(known.keys)
    constraints = reduceConstraints(constraints, known)
    for (allergen in constraints.map{c -> c.allergen}) {
      val constraintIngredients = constraints
        .filter { c -> c.allergen == allergen }
        .map { c -> c.ingredients.toSet() }
      val ingredientInEvery = constraintIngredients.reduce(Set<String>::intersect)
      if (ingredientInEvery.size == 1) {
        known[allergen] = ingredientInEvery.last()
      }
    }
  }
  return Result(known, constraints)
}

fun reduceConstraints(
  constraints: List<Constraint>,
  known: MutableMap<Allergen, Ingredient>
): List<Constraint> {
  val knownAllergens = known.keys
  val knownIngredients = known.values
  val result = constraints.filter { c ->
    !knownAllergens.contains(c.allergen)
  }
  return result.mapNotNull{c ->
    val ings = c.ingredients.toMutableList()
    ings.removeAll(knownIngredients)
    if(ings.isEmpty()) null else Constraint(ings, c.allergen)
  }
}

fun sumSafeIngredients(
  constraints: List<Constraint>,
  allIngredients: Set<Ingredient>,
  allAllergens: Set<Allergen>,
  ingCount: Map<Ingredient, Int>,
): Int {
  val safeIngredients = safeIngredients(constraints, allIngredients, allAllergens)
  return safeIngredients.map { ing -> ingCount[ing]!! }.sum()
}

fun day21pt1(pathname: String): Int {
  val parsed = parseFoodDetails(File(pathname).readLines())
  return sumSafeIngredients(
    parsed.constraints, parsed.allIngredients, parsed.allAllergens, parsed.ingCount)
}

fun day21pt1(): Int {
  return day21pt1("input/day21.txt")
}

fun day21pt2(pathname: String): String {
  val parsed = parseFoodDetails(File(pathname).readLines())
  val knownResult = knownIngredients( parsed.constraints, parsed.allAllergens)
  val known = knownResult.known
  val knownAlpha = known.entries.sortedBy{ c -> c.key }.map{c -> c.value}
  return knownAlpha.joinToString(separator = ",")
}

fun day21pt2(): String {
  return day21pt2("input/day21.txt")
}

fun main() {
  println("Part 1: " + day21pt1())
  println("Part 2: " + day21pt2())
}