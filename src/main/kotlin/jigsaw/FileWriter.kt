package jigsaw

import java.io.File

fun outputFile(pathname: String): String{
  return pathname.replace("input", "tmp")
}

fun writeToFile(pathname: String, picture: Picture){
  File("tmp").mkdirs()
  val file = File(pathname)
  file.createNewFile()
  file.bufferedWriter().use { out ->
    for(x in 0 until picture.size){
      for(y in 0 until picture.size){
        val tile = picture.tiles[Coord(x, y)]!!
        out.write("Tile ${tile.id}:\n")
        out.write(tile.pixelsToString)
        out.newLine()
      }
    }
  }
}

fun writeToFile(pathname: String, rows: List<String>){
  File("tmp").mkdirs()
  val file = File(pathname)
  file.createNewFile()
  file.bufferedWriter().use { out ->
    for(row in rows){
      out.write(row)
      out.newLine()
    }
  }
}

