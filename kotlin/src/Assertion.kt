/**
 * @author Artur Bosch
 */

fun main(args: Array<String>) {
	5 matches 5
	5 matches 10
	5 matches ""
}

infix fun <T> T.matches(that: T) {
	if (this == that)
		println("SUCCESS")
	else
		println("FAIL: '$this' does not match '$that'!")
}
