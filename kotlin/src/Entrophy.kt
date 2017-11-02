/**
 * @author Artur Bosch
 */
/**
 * Haeufigkeitsverteilung des englischen Alphabets als Ziffern von 0-25.
 *
 * Quelle: Vorlesung
 */
private val englishDistribution = mapOf(
		0 to 8.2, 1 to 1.5, 2 to 2.8, 3 to 4.2, 4 to 12.7, 5 to 2.2, 6 to 2.0, 7 to 6.1,
		8 to 7.0, 9 to 0.1, 10 to 0.8, 11 to 4.0, 12 to 2.4, 13 to 6.7, 14 to 7.5,
		15 to 1.9, 16 to 0.1, 17 to 6.0, 18 to 6.3, 19 to 9.0, 20 to 2.8, 21 to 1.0,
		22 to 2.4, 23 to 0.1, 24 to 2.0, 25 to 0.1)

/**
 * Haeufigkeitsverteilung fuer Bigramme fuer den Buchstaben E.
 *
 * Quelle: http://www.mathe.tu-freiberg.de/~hebisch/cafe/kryptographie/bigramme.html
 */
private val englishDistributionAfterE = mapOf(
		0 to 65, 1 to 11, 2 to 64, 3 to 107, 4 to 39, 5 to 23, 6 to 20, 7 to 15,
		8 to 40, 9 to 1, 10 to 2, 11 to 46, 12 to 43, 13 to 120, 14 to 46,
		15 to 32, 16 to 14, 17 to 154, 18 to 145, 19 to 80, 20 to 7, 21 to 16,
		22 to 41, 23 to 17, 24 to 17)

fun main(args: Array<String>) {
	val entrophy = -englishDistribution
			.map { it.value / 100 } // umrechnung auf Prozente
			.map { it * (Math.log(it) / Math.log(2.0)) }
			.sum()

	val maxEntrophy = -englishDistribution
			.map { 1.toDouble() / 26 }
			.map { it * (Math.log(it) / Math.log(2.0)) }
			.sum()

	// Die Reihe von E aufaddiert
	val bigramCountForE = 1165.0

	val afterE = -englishDistributionAfterE
			.map { it.value.toDouble() / bigramCountForE }
			.map { it * (Math.log(it) / Math.log(2.0)) }
			.sum()

	val afterQ = -1.0 * (Math.log(1.0) / Math.log(2.0))

	println(entrophy)
	println(maxEntrophy)
	println(afterE)
	println(afterQ)
}
