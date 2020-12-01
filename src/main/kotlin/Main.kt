fun day1From2017(): Long {
  val numberList = asNumberList("input/input2017Day1Pt1.txt")
  return inverseCaptcha(numberList)
}

fun day1pt1(): Long {
  val numberList = asNumberList("input/test0.txt")
  return 0L;
}

fun main(){
  println(day1pt1())
}