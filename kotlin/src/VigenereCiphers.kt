import java.util.ArrayList

/**
 * Einstiegspunkt des Programs.
 */
fun main(args: Array<String>) {
	vigenereCipher(CIPHER_TEXT)
}

/**
 * Fuehrt eine Vigenere-Dechiffrierung aus. Die notwendigen Schritte sind:
 *
 * 0. Den Text von unnoetigen Zeichen befreien/normalisieren.
 * 1. Den Text in Trigramme aufteilen.
 * 2. Die Distanz von mehrfachauftrettene Trigrammen bestimmen.
 * 3. Alle Teiler von Distanzen bestimmen.
 * 4. Die Schluessellaenge ergibt sich aus dem haeufigsten vorkommenden Teiler.
 * 5. Den normalisierten Ciphertext in |KeyLength|-Stuecke einteilen.
 * 6. Jedes Stueck Char-weise abhaengig vom Index in |KeyLength|-Gruppen einteilen.
 * 7. Haeufigkeitsanalyse der Buchstaben je Gruppe durchfuehren.
 * 8. Statistische Distanz bestimmen und so den Schluessel rausfinden.
 * 9. Ciphertext entschluesseln.
 */
fun vigenereCipher(cipherText: String) {
	val normalized = normalize(cipherText)
	val distances = analyzeTrigramsForDistances(normalized)
	val factors = decomposeToFactors(distances)
	val keyLength = findKeyLength(factors)
	val key = extractKey(normalized, keyLength)
	val decryptedText = decryptText(normalized, key)

	println(normalized)
	println(distances)
	println(factors)
	println(keyLength)
	println(key)
	println(decryptedText)
}

/**
 * Entfernt alle unnoetigen Zeichen des Ciphertextes.
 */
private fun normalize(cipherText: String): String = cipherText
		.mapNotNull { if (it in delimiters) null else it }
		.map { it.toLowerCase() }
		.joinToString("")

private val delimiters = ".,:;'‘’\n ".toCharArray()

/**
 * Ermittelt die Distanzen aller Trigramme im normalisierten Ciphertext.
 */
private fun analyzeTrigramsForDistances(normalized: String): Set<Int> {
	val distances = mutableSetOf<Int>()
	val step = 2 // Index plus step ergibt die Position eines Trigrams

	/**
	 * Der StartIndex zeigt welches Trigram gerade gesucht wird.
	 * Fuer alle weiteren Vorkommen dieses Trigrams im Text, wird der Abstand zum StartIndex ermittelt.
	 * Dieser ist die Distanz.
	 */
	fun findDistance(startIndex: Int) {
		val endIndex = startIndex + step
		val last = normalized.substring(startIndex, endIndex + 1)
		// Die Positionen nach dem gerade bearbeitenden Trigram werden betrachtet.
		for (i in ((endIndex + 1)..(normalized.length - (step + 1)))) {
			val currentEnd = i + step
			val current = normalized.substring(i, currentEnd + 1)
			if (current == last) {
				val distance = currentEnd - endIndex
				distances.add(distance)
			}
		}
	}
	// Zeichen fuer Zeichen werden Trigramme aufgebaut
	for (index in (0 until normalized.length - step)) {
		findDistance(index)
	}
	return distances
}

/**
 * Findet alle Faktoren zu jeder Distanz. Sehr unoptimiert wird jede Zahl
 * zwischen 2 und Faktor als moeglicher Teiler getestet.
 */
private fun decomposeToFactors(distances: Set<Int>): MutableList<Int> {
	val factors = mutableListOf<Int>()
	for (value in distances) {
		(2..value).filterTo(factors) { value % it == 0 }
	}
	return factors
}

/**
 * Welcher Faktor kommt am Haeufigsten vor? Dieser ist die Schluessellaenge.
 */
private fun findKeyLength(factors: List<Int>): Int {
	val factorByCount = factors.groupBy { it }
			.mapValues { it.value.size }

	val keyLength = factorByCount.entries
			.maxBy { it.value }?.key

	return keyLength ?: throw IllegalStateException("No factors present, check your distances!")
}

/**
 * Normalisierten Ciphertext in |KeyLength|-Stuecke einteilen.
 * Anschliessend werden Sub-Texte abhaengig des Indexes der Schluessellaenge gebildet.
 */
private fun extractKey(normalized: String, keyLength: Int): String {
	val partsByKeyLength = mutableListOf<String>()
	(0 until normalized.length - keyLength step keyLength)
			.mapTo(partsByKeyLength) { normalized.substring(it, it + keyLength) }
	// Wenn Text nicht in genau "text.length % keyLength == 0" Stuecke eingeteilt werden kann,
	// werden die letzten Buchstaben mit Leerzeichen auf die KeyLength gebracht.
	padRemainingLettersToKeyLength(normalized, keyLength, partsByKeyLength)

	// Indexweise Sub-Texte bilden
	val result = (0 until keyLength).map { index ->
		partsByKeyLength.map { it[index] }
				.joinToString("").trim() // Vorherige Spaces entfernen
	}
	// Shifts aller Einzeltexte durch statistische Distanz bestimmen
	return result.map { findBestMatchingShiftCharacter(it) }
			.joinToString("")
}

/**
 * Zu wenige Zeichen am Ende? -> Mit Spaces auffuehlen.
 */
private fun padRemainingLettersToKeyLength(normalized: String, keyLength: Int,
										   partsByKeyLength: MutableList<String>) {
	val remainder = normalized.length % keyLength
	val startRemainder = normalized.length - remainder
	var lastString = normalized.substring(startRemainder)
	lastString = lastString.padEnd(keyLength, ' ')
	partsByKeyLength.add(lastString)
}

private fun findBestMatchingShiftCharacter(text: String): Char {
	val histogram = createHistogram(text)
	val percents = extractProbabilities(histogram)
	val minDistance = evaluateStatisticDistances(percents)
	return (minDistance + 97).toChar()
}

/**
 * Erstellt eine Map mit Buchstabe zu Vorkommen.
 */
private fun createHistogram(text: String): Map<Char, Int> {
	return text.filter { it.isLetter() }
			.groupBy { it.toLowerCase() }
			.mapValues { it.value.size }
}

/**
 * Wandelt Vorkommen zum Prozentwert um in Abhaengigkeit zur Summe aller Vorkommen.
 */
private fun extractProbabilities(histogram: Map<Char, Int>): Map<Char, Double> {
	val sum = histogram.values.sum()
	return histogram.mapValues { (_, value) -> value.toDouble() / sum * 100 }
}

/**
 * Berechnet erst die statistische Distanz fuer jeden moeglichen Shift.
 * Der kleinste Wert weist den Buchstaben mit der groessten Wahrscheinlichkeit auf.
 */
private fun evaluateStatisticDistances(percents: Map<Char, Double>): Int {
	val distances = (0..25).mapTo(ArrayList()) { shift ->
		shift to calculateStatisticDistance(percents, shift)
	}
	val minDistance = distances.minBy { it.second }?.first
	return minDistance ?: throw IllegalStateException("Could not find minimal distance!")
}

/**
 * Distanzfunktion aus der Vorlesung.
 *
 * Bemerkung: Englische Buchstaben sind schon als Integer-Werte abgebildet,
 * Die des Ciphertextes muessen erstmal auf Integer-Werte gebracht werden.
 */
private fun calculateStatisticDistance(distribution: Map<Char, Double>, shift: Int): Double {
	return 0.5 * (0..25).map { letter ->
		val englishLetterFrequency = englishDistribution[letter] ?: 0.0
		val currentLetterFrequency = distribution[(((letter + shift) % 26) + 97).toChar()] ?: 0.0
		Math.abs(englishLetterFrequency - currentLetterFrequency)
	}.sum()
}

/**
 * Haefigkeitsverteilung des englischen Alphabets als Ziffern von 0-25.
 */
private val englishDistribution = mapOf(
		0 to 8.2, 1 to 1.5, 2 to 2.8, 3 to 4.2, 4 to 12.7, 5 to 2.2, 6 to 2.0, 7 to 6.1,
		8 to 7.0, 9 to 0.1, 10 to 0.8, 11 to 4.0, 12 to 2.4, 13 to 6.7, 14 to 7.5,
		15 to 1.9, 16 to 0.1, 17 to 6.0, 18 to 6.3, 19 to 9.0, 20 to 2.8, 21 to 1.0,
		22 to 2.4, 23 to 0.1, 24 to 2.0, 25 to 0.1)

/**
 * Wandelt jeden Character mit Hilfe des Schluessels 'Schluessellaengen-weise' in den Originaltext um.
 */
private fun decryptText(normalized: String, key: String): String {
	val decryptedChars = StringBuilder()
	var currentKeyIndex = 0
	for (currentChar in normalized) {
		val keyValue = key[currentKeyIndex] - 'a'
		val transformedChar = transformChar(currentChar, keyValue)
		decryptedChars.append(transformedChar)
		currentKeyIndex = (currentKeyIndex + 1) % key.length
	}
	return decryptedChars.toString()
}

private fun transformChar(currentChar: Char, keyValue: Int): Char {
	var transformedChar = ((currentChar - 'a' - keyValue) % 26) + 97
	if (transformedChar < 97) transformedChar += 26
	return transformedChar.toChar()
}

const val CIPHER_TEXT = """
Pz px souwhwz btoe vzyxnkyw,
Mhphd uua nabs, hhw tls cl feuxxw,
Toum rob btoe iom llbguxr’k bxke
Dbbee abxle cclboum wbd hjixay.
Ugw tocl pehe tgd pxex toyfx,
Nv ghke fcxedphz uua u wkehg,
Zxnafxl, dv hhm rljkxhlhw:
bf fin iayxhg, wl qbel tygw:
Aux, tl I hg tg hvhxlt Wovd,
Im qx aacy ngehlgxd sovd
Nvq mh ‘sjuix toy lxrwygm’s aigzul,
Qx pisf ftkl ufxnkm xke sigz;
Esmx mhl Jnvk h fbtr juee;
Sv, ahhd uczat bhmh yvo tel.
Ncox ml shnr ougws, pz px bl zkbeuxl,
Tnk Lhuiu matls lxltvlx tmlhwl.
"""
