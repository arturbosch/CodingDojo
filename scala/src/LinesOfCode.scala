import java.io.Closeable

import scala.io.Source

/**
  * @author Artur Bosch
  */
object LinesOfCode {

  def linesOfCode(path: String): Int = {

    def isImportOrPackage(line: String) = line.startsWith("import") || line.startsWith("package")
    def isComment(line: String) = line.startsWith("/*") || line.startsWith("*") || line.startsWith("//")
    def isBraceOrSemicolon(line: String) = line match {
      case ";" | "}" | "{" => true
      case _ => false
    }

    using(Source.fromFile(path)) { stream =>
      stream.getLines()
        .map(line => line.trim)
        .filterNot(line => line.isEmpty)
        .filterNot(line => isComment(line))
        .filterNot(line => isImportOrPackage(line))
        .filterNot(line => isBraceOrSemicolon(line))
        .count(_ => true)
    }
  }

  def using[R <: Closeable, T](stream: R)(f: R => T): T =
    try {
      f(stream)
    } finally {
      stream.close()
    }

  def main(args: Array[String]): Unit = {
    val path = "./scala/src/FizzBuzz.scala"
    val loc = linesOfCode(path)
    println(s"File: $path - LOC: $loc")
  }
}
