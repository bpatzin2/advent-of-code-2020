import java.io.BufferedReader
import java.io.File

fun asNumberList(pathname: String): List<Int> {
  val inputString = asString(pathname)
  return inputString.map(Character::getNumericValue)
}

fun asString(pathname: String): String {
  val bufferedReader: BufferedReader = File(pathname).bufferedReader()
  return bufferedReader.use { it.readText() }.trim()
}