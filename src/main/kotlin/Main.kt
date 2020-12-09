import days.*

fun main(args: Array<String>){
  if(!args.contains("--all")){
    println(day8pt2())
    return
  }
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
}