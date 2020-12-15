import days.*

fun printSlow(printSlow: Boolean, str: String, function: () -> Any){
  if(printSlow){
    println(str + function())
  }else{
    println(str + "works but is slow")
  }
}

fun main(args: Array<String>) {
  val withSlow = args.contains("--with-slow")

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
  printSlow(withSlow,"Day 11 Part 1: "){ day11pt1() }
  printSlow(withSlow, "Day 11 Part 2: "){ day11pt2() }
  println("Day 12 Part 1: " + day12pt1())
  println("Day 12 Part 2: " + day12pt2())
  println("Day 13 Part 1: " + day13pt1())
  println("Day 13 Part 2: " + day13pt2())
  println("Day 14 Part 1: " + day14pt1())
  println("Day 14 Part 2: " + day14pt2())
  println("Day 15 Part 1: " + day15pt1())
  printSlow(withSlow, "Day 15 Part 2: ") { day15pt2() }
}