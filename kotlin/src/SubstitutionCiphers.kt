import java.util.HashMap

/**
 * @author Artur Bosch
 */

val text = """
Yit jgst jn vsghu, yit jgst ioa afoyt qfftqsa.
Viqy, ror it jqssn jt yg zqjoai jt?
Wtuuqsa yiqy egjt xhyg jn zqyits’a rggs
Xfgh thystqyn iqct q fstathy qkja;
Oz hgy, tkatvitst yitn jtty voyi eiqsoyn;
Wxy O, vig htcts lhtv igv yg thystqy,
Hgs htcts httrtr yiqy O aigxkr thystqy,
Qj ayqsc’r zgs jtqy, uorrn zgs kqel gz akttf;
Voyi gqyia ltfy vqlohu, qhr voyi wsqvkohu ztr;
Qhr yiqy vioei afoyta jt jgst yiqh qkk yitat vqhya-
It rgta oy xhrts hqjt gz ftsztey kgct;
Qa vig aigxkr aqn, oz O aigxkr akttf gs tqy,
’Yvtst rtqrkn aoelhtaa gs tkat fstathy rtqyi.
O fsoyitt ug qhr uty jt agjt stfqay;
O eqst hgy viqy, ag oy wt vigktagjt zggr.
"""

val letterFrequencies = listOf('e', 't', 'o', 'a', 'n', 'i', 'r', 's', 'h', 'l', 'd', 'c', 'u', 'j', 'm', 'f', 'g', 'w', 'y', 'p', 'v', 'k', 'b', 'z', 'x', 'q')

fun histogram(text: String): Map<Char, Int> {
	val map = HashMap<Char, Int>()
	for (c in text) {
		if (c.isLetter()) map.merge(c.toLowerCase(), 1) { v1, v2 -> v1 + v2 }
	}
	return map
}

fun String.substitute(which: Char, replace: Char): String = this.replace(which, replace)

fun String.substitute(rules: List<Pair<Char, Char>>): String {
	var result = ""
	for (c in this) if (c.isLetter()) result += rules.find { it.first == c.toLowerCase() }?.second ?: c else result += c
	return result
}

fun String.peek() = apply { println(this) }

fun main(args: Array<String>) {
	val histogram = histogram(text).entries.sortedBy { it.value }.asReversed()
//	val alphabet = histogram.entries.sortedBy { it.value }.reversed().map { it.key }
	println(histogram)
	println(letterFrequencies)
	println(text)
//	var message = ""
//	for (c in text) {
//		if (c.isLetter().not()) message += c
//		val indexOf = alphabet.indexOf(c)
//		message += if (indexOf == -1) "" else letterFrequencies[indexOf]
//	}
	val message = text.substitute(listOf(
			't' to 'e',
			'y' to 't',
			'i' to 'h',
			'q' to 'a',
			'g' to 'o',
			'o' to 'i',
			'a' to 's',
			'v' to 'w',
			'i' to 'n'
			))
	println(message)
}