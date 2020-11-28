import java.io.File

fun run(): Int {
    return 4
}

fun main() {
    println("hello world")
    File("input/test0.txt").forEachLine { println(it) }
    return
}