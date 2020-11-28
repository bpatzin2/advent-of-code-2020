fun day1From2017(): Long {
  val numberList = asNumberList("input/input2017Day1Pt1.txt")
  return inverseCaptcha(numberList)
}

fun main(){
  println(day1From2017())
}