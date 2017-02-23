import java.io.File

/**
 * @author Artur Bosch
 */

fun main(args: Array<String>) {

	loop@ for (i in 1..100) {
		for (j in 1..100) {
			if (i == j && i == 5) break@loop
			println("$i - $j")
		}
	}

	(1..100).forEach {
		if (it == 50) return@forEach
	}

	println((5 to 20) + (2 to -7))
	File("").readText()

//	var a = 5
//	var b = (a = 3)
}

fun Any.typeString(): String = when (this) {
	is Int -> "Int"
	is Double -> "Double"
	is String -> "String"
	else -> "Object"
}


operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = first + other.first to second + other.second