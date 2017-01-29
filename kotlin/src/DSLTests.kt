/**
 * @author Artur Bosch
 */
open class Tag(val name: String) {
	private val children = mutableListOf<Tag>()

	protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
		child.init()
		children.add(child)
	}

	override fun toString() =
			"<$name>${children.joinToString("")}</$name>"
}

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

class TABLE : Tag("table") {
	fun tr(init: TR.() -> Unit) = doInit(TR(), init)
}
class TR : Tag("tr") {
	fun td(init: TD.() -> Unit) = doInit(TD(), init)
}
class TD : Tag("td")

fun createTable() =
		table {
			tr {
				td {
				}
			}
		}

fun main(args: Array<String>) {
	val s = build {
		append(1)
		append(2)
		appendExcl()
	}

	println(s)

	println(createTable())
}

fun build(sb: StringBuilder.() -> Unit): String {
	val builder = StringBuilder()
	builder.sb()
	return builder.toString()
}

val appendExcl : StringBuilder.() -> Unit =
		{ this.append("!") }