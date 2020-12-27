package fileinput

import java.io.BufferedReader
import java.io.File

fun linesAsCharList(pathname: String): List<List<Char>> {
  val strList = File(pathname).readLines()
  return strList.map{s -> s.toList()}
}

fun asNumberListByNewLine(pathname: String): List<Int> {
  val strList = File(pathname).readLines()
  return strList.map(String::toInt)
}

fun asString(pathname: String): String {
  val bufferedReader: BufferedReader = File(pathname).bufferedReader()
  return bufferedReader.use { it.readText() }.trim()
}