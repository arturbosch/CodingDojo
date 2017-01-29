import java.time.LocalDate
import java.util.Scanner

/**
 * https://adriann.github.io/programming_problems.html
 *
 * @author Artur Bosch
 */
fun main(args: Array<String>) {
	exerciseOne()
	exerciseTwo()
	exerciseThree()
	exerciseFour()
	exerciseFive()
	exerciseSix()
	exerciseSeven()
	exerciseEight()
	exerciseNine()
	exerciseTen()
}

interface A {
	fun method() {
		println()
	}
}

interface B {
	fun method() {
		println()
	}
}

private fun exerciseOne() {
	println("Hello World!")
}

private fun exerciseTwo() {
	println("Enter your name: ")
	val scanner = Scanner(System.`in`)
	val name = scanner.next()
	println("Hello $name")
}

private fun exerciseThree() {
	println("Enter your name: ")
	val scanner = Scanner(System.`in`)
	val name = scanner.next()
	val realName = when (name) {
		"Alice", "Bob" -> name
		else -> "World"
	}
	println("Hello $realName")
}

private fun exerciseFour() {
	println("Enter a number:")
	readLine()?.toInt()?.let {
		println((1..it).sum())
	}
}

private fun exerciseFive() {
	println("Enter a number:")
	readLine()?.toInt()?.let {
		println((1..it).filter {
			when {
				it % 3 == 0 || it % 5 == 0 -> true
				else -> false
			}
		}.sum())
	}
}

private fun exerciseSix() {
	println("Enter a number:")
	readLine()?.toInt()?.let {
		val number = it
		println("Enter 'sum' or 'product':")
		when (readLine()) {
			"sum" -> println((1..number).sum())
			"product" -> println((1..number).fold(1) { product, current -> product * current })
			else -> println("Enter 'sum' or 'product'!")
		}

	}
}

private fun exerciseSeven() {
	(1..12).forEach { x ->
		(1..12).forEach { y ->
			println("$x*$y = ${x * y}")
		}
	}
}

private fun exerciseEight() {
	fun Int.isPrime(): Boolean = when {
		this == 1 || this == 2 -> true
		(2..this - 1).any { this % it == 0 } -> false
		else -> true
	}

	val n = 200
	(1..n).filter(Int::isPrime).forEach { println("$it ") }
}

private fun exerciseNine() {
	// guessing game
	var guesses = 0
	var found = false
	val random = (Math.random() * 100).toInt()
	while (!found) {
		println("Guess the number (tries=$guesses):")
		guesses++
		val guess = readLine()?.toInt() ?: -1
		if (guess == random) {
			println("You found the secret number $random (tries=$guesses)!")
			found = true
		} else {
			val greater = if (guess > random) "smaller" else "greater"
			println("The secret number is $greater than your guess!")
		}
	}
}

private fun exerciseTen() {
	// Write next 20 leap years
	generateSequence(LocalDate.now()) { it.plusYears(1) }
			.filter { it.isLeapYear }
			.map { it.year }
			.take(20)
			.iterator()
			.forEach(::println)
}