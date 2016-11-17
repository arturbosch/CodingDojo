fun main(args: Array<String>) {
	fizzbuzz(100)
}

fun fizzbuzz(n: Int): Unit {
	(1..n).forEach {
		println(fizzOrBuzz(it))
	}
}

private fun fizzOrBuzz(it: Int): Any {
	return when {
		it.divides(15) -> "FizzBuzz"
		it.divides(5) -> "Buzz"
		it.divides(3) -> "Fizz"
		else -> it
	}
}

fun Int.divides(i: Int): Boolean {
	return this % i == 0
}
