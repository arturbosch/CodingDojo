fun main(args: Array<String>) {
	val encrytedText = TEXT
			.map { charAsInt(it) }
			.map { dec(it) }
			.map { intToChar(it) }
			.joinToString("")
	println(encrytedText)

	println("#inverse: " + (1..25).map { findInverse(it) }.filter { it > 0 }.count())
	println("#inverse: " + (1..28).map { findInverse(it, 29) }.filter { it > 0 }.count())
	println("#inverse: " + (1..29).map { findInverse(it, 30) }.filter { it > 0 }.count())
}

const val TEXT = "cvevbfsmfsdpjusqvlsofhwvilssv"

const val a = 17
const val ainv = 23
const val b = 5
const val m = 26

fun enc(x: Int): Int = (a * x + b) % m
fun dec(cx: Int): Int {
	val result = ainv * (cx - b) % m
	return if (result < 0) result + m else result
}

fun charAsInt(x: Char): Int = x.toInt() - 'a'.toInt()
fun intToChar(x: Int): Char = (x + 'a'.toInt()).toChar()

fun findInverse(a: Int, rem: Int = 26): Int {
	var x = 1
	while (true) {
		val mod = (a * x) % rem
		if (mod == 0) {
			return 0
		}
		if (mod == 1) {
			break
		}
		x++
	}
	return x
}
