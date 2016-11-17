/**
  * @author artur
  */
object Maths {

  def sqrt(x: Double) = {

    def abs(x: Double): Double =
      if (x < 0) -x else x

    def isGoodEnough(guess: Double): Boolean =
      abs(guess * guess - x) / x < 0.001

    def improve(guess: Double): Double =
      abs(guess - x / guess) / 2

    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    sqrtIter(1)
  }

  def gcd(a: Int, b: Int): Int =
    if(b == 0) a else gcd(b, a % b)


  def factorial(x: Int): Int = {

    def fac(result: Int, next: Int): Int =
      if (next == 0) result else fac(result * next, next - 1)

    fac(1, x)
  }
}
