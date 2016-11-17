import java.io.File

/**
 * @author Artur Bosch
 */

fun main(args: Array<String>) {
	val path = "./kotlin/src/FizzBuzz.kt"
	val loc = linesOfCode(path)
	println("File: ${path.split("/").last()} - LOC: $loc")
}

fun linesOfCode(path: String): Int {

	fun String.isImportOrPackage() = startsWith("import") || startsWith("package")
	fun String.isComment() = startsWith("/*") || startsWith("*") || startsWith("//")
	fun String.isBraceOrSemicolon() = when (this) {
		"}", "{", "," -> true
		else -> false
	}

	return File(path).useLines { lines ->
		lines.map(String::trim)
				.filterNot(String::isBlank)
				.filterNot(String::isComment)
				.filterNot(String::isImportOrPackage)
				.filterNot(String::isBraceOrSemicolon)
				.count()
	}
}
