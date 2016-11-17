/**
  * @author Artur Bosch
  */
object FizzBuzz {

  implicit class NumberExt(number: Int) {
    def dividesBy(n: Int): Boolean = number % n == 0
  }

  def fizzbuzz(n: Int): Unit = {

    def cases(i: Int): String = {
      if (i dividesBy 15) return "fizzbuzz"
      if (i dividesBy 5) return "buzz"
      if (i dividesBy 3) "fizz" else i.toString
    }

    (1 to n).foreach(i =>
      println(cases(i))
    )
  }

  def main(args: Array[String]): Unit = {
    fizzbuzz(100)
  }
}
