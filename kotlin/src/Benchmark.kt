import kotlin.system.measureTimeMillis

/**
 * @author Artur Bosch
 */

fun main(args: Array<String>) {
	val time = benchmark { linesOfCode("./kotlin/src/LinesOfCode.kt") }
	println("Time: $time ms")
}

fun benchmark(block: () -> Unit): Long {
	return (1..10).map {
		measureTimeMillis {
			block()
		}
	}.sum() / 10
}