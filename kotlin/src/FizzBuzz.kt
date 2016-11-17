/**
 * @author Artur Bosch
 */

fun main(args: Array<String>) {
	fizzbuzz(100)
}

fun fizzbuzz(n: Int): Unit {

	fun Int.dividesBy(n: Int): Boolean = this % n == 0

	fun fizzOrBuzz(it: Int): Any {
		return when {
			it.dividesBy(15) -> "FizzBuzz"
			it.dividesBy(5) -> "Buzz"
			it.dividesBy(3) -> "Fizz"
			else -> it
		}
	}

	(1..n).forEach {
		println(fizzOrBuzz(it))
	}
}

