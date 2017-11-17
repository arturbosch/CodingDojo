/**
 * @author Artur Bosch
 */
fun main(args: Array<String>) {
//	val i = findInverse(8, 32); println(i)

	elementOrder(2, 17).apply {
		println("set: $this with order: $size") // 8
	}
	elementOrder(5, 17).apply {
		println("set: $this with order: $size") // 16
	}
	elementOrder(7, 17).apply {
		println("set: $this with order: $size") // 16
	}
	elementOrder(5, 26).apply {
		println("set: $this with order: $size") // 4
	}
	elementOrder(11, 26).apply {
		println("set: $this with order: $size") // 12
	}
	elementOrder(17, 26).apply {
		println("set: $this with order: $size") // 6
	}

	println()

	generatorsFor(495)
	// Orders: [1, 2, 3, 4, 5, 6, 10, 12, 15, 20, 30, 60]

	generatorsFor(47)
	// Orders: [1, 2, 23, 46]
	// Generatoren: [5, 10, 11, 13, 15, 19, 20, 22, 23, 26, 29, 30, 31, 33, 35, 38, 39, 40, 41, 43, 44, 45]
	// #Generatoren: 22
}

/**
 * Die Ordnung eines Elementes a ergibt sich aus der Anzahl der Elemente aus der Gruppe mit dem Modulus mod, die sie
 * erzeugen kann.
 *
 * Wir multiplizieren sooft a mit sich selber und speichern das Ergebnis in einem Set bis ein Element sich wiederholt
 * also kein Erzeuger sein kann oder schon alle Elemente erzeugt worden sind (Anzahl der Elemente entspricht dem
 * Modulus).
 */
fun elementOrder(a: Int, mod: Int): Set<Int> {
	val results = mutableSetOf<Int>()

	var step = 1
	do {
		step = (step * a) % mod
		if (step in results) {
			break
		}
		results.add(step)
	} while (results.size != mod)

	return results
}

/**
 * Bestimmt die Ordnungen und Generatoren fuer eine Gruppe mit Modulus 'remainder'.
 *
 * 1. Finde alle Elemente in (Z remainder, *), die ein Inverses haben, um auch den Z* Fall bei b) zu beruecksichtigen.
 * 2. Bestimme die Elementordung fuer jedes Element aus 1).
 * 3. Jetzt entfernen wir alle doppelten Ordnungen und erhalten alle vorkommenden Ordnungen in der betrachteten Gruppe.
 * 4. Alle Elemente deren Ordnung die Anzahl der Elemente der Gruppe ohne 0 ist, sind Erzeuger der Gruppe.
 */
fun generatorsFor(remainder: Int) {
	val elements = elementsInGroupThatHaveAnInverse(remainder)
	val elemToElemOrder = elements.map { it to elementOrder(it, remainder).size }
	val distinctOrders = elemToElemOrder.map { (_, order) -> order }.toSet().sorted()
	val generators = elemToElemOrder.filter { (_, order) -> order == remainder - 1 }
			.map { (elem, _) -> elem }

	println("Orders: $distinctOrders")
	println("Generatoren: $generators")
	println("#Generatoren: ${generators.size}")
}

private fun elementsInGroupThatHaveAnInverse(remainder: Int): List<Int> {
	return (1 until remainder)
			.map { elem -> elem to findInverse(elem, remainder) }
			.filter { (_, inv) -> inv != 0 }
			.map { (elem, _) -> elem }
}
