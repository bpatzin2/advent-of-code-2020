package fileinput

import passwordpolicy.PasswordWithPolicy
import passwordpolicy.toPasswordWithPolicy
import util.asDigitList
import java.io.BufferedReader
import java.io.File

fun asDigitList(pathname: String): List<Int> {
  val inputString = asString(pathname)
  return asDigitList(inputString)
}

fun asNumberListByNewLine(pathname: String): List<Int> {
  val strList = File(pathname).readLines()
  return strList.map(String::toInt)
}

fun toPasswordsWithPolicies(pathname: String): List<PasswordWithPolicy> {
  val strList = File(pathname).readLines()
  return strList.map(::toPasswordWithPolicy)
}

fun asString(pathname: String): String {
  val bufferedReader: BufferedReader = File(pathname).bufferedReader()
  return bufferedReader.use { it.readText() }.trim()
}