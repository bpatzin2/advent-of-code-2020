import days.*

fun printSlow(skipSlow: Boolean, str: String, function: () -> Any){
  if(skipSlow){
    println(str + "works but is slow")
  }else{
    print(str + "calculating slowly... ")
    println(function())
  }
}

fun main(args: Array<String>) {
  val skipSlow = args.contains("--skip-slow")

  println("Day 1 Part 1: " + day1pt1())
  println("Day 1 Part 2: " + day1pt2())
  println("Day 2 Part 1: " + day2pt1())
  println("Day 2 Part 2: " + day2pt2())
  println("Day 3 Part 1: " + day3pt1())
  println("Day 3 Part 2: " + day3pt2())
  println("Day 4 Part 1: " + day4pt1())
  println("Day 4 Part 2: " + day4pt2())
  println("Day 5 Part 1: " + day5pt1())
  println("Day 5 Part 2: " + day5pt2())
  println("Day 6 Part 1: " + day6pt1())
  println("Day 6 Part 2: " + day6pt2())
  println("Day 7 Part 1: " + day7pt1())
  println("Day 7 Part 2: " + day7pt2())
  println("Day 8 Part 1: " + day8pt1())
  println("Day 8 Part 2: " + day8pt2())
  println("Day 9 Part 1: " + day9pt1())
  println("Day 9 Part 2: " + day9pt2())
  println("Day 10 Part 1: " + day10pt1())
  println("Day 10 Part 2: " + day10pt2())
  printSlow(skipSlow,"Day 11 Part 1: "){ day11pt1() }
  printSlow(skipSlow, "Day 11 Part 2: "){ day11pt2() }
  println("Day 12 Part 1: " + day12pt1())
  println("Day 12 Part 2: " + day12pt2())
  println("Day 13 Part 1: " + day13pt1())
  println("Day 13 Part 2: " + day13pt2())
  println("Day 14 Part 1: " + day14pt1())
  println("Day 14 Part 2: " + day14pt2())
  println("Day 15 Part 1: " + day15pt1())
  printSlow(skipSlow, "Day 15 Part 2: ") { day15pt2() }
  println("Day 16 Part 1: " + day16pt1())
  println("Day 16 Part 2: " + day16pt2())
  println("Day 17 Part 1: " + day17pt1())
  println("Day 17 Part 2: " + day17pt2())
  println("Day 18 Part 1: " + day18pt1())
  println("Day 18 Part 2: " + day18pt2())
  println("Day 19 Part 1: " + day19pt1())
  println("Day 19 Part 2: " + day19pt2())
  printSlow(skipSlow,"Day 20 Part 1: "){ day20pt1() }
  printSlow(skipSlow,"Day 20 Part 2: "){ day20pt2() }
  println("Day 21 Part 1: " + day21pt1())
  println("Day 21 Part 2: " + day21pt2())
  println("Day 22 Part 1: " + day22pt1())
  println("Day 22 Part 2: " + day22pt2())
  println("Day 23 Part 1: " + day23pt1())
  println("Day 23 Part 2: " + day23pt2())
  println("Day 24 Part 1: " + day24pt1())
  println("Day 24 Part 2: " + day24pt2())
  println("Day 25 Part 1: " + day25pt1())
  println("Day 25 Part 2: " + "FREE STAR")
}