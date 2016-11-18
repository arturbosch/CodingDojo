/**
  * @author Artur Bosch
  */
object Benchmark {

  def main(args: Array[String]): Unit = {
    val time: Long = benchmark(() => LinesOfCode.linesOfCode("./scala/src/LinesOfCode.scala"))
    println(s"Time: $time ms")
  }

  def benchmark(block: () => Unit): Long = {
    (1 to 10).map(n => measure(block)).sum / 10
  }

  def measure(block: () => Unit): Long = {
    val start: Long = System.currentTimeMillis()
    block()
    System.currentTimeMillis() - start
  }
}
