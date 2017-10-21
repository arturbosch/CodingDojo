import java.util.HashMap

/**
 * @author Artur Bosch
 */

val text = """
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

val letterFrequencies = listOf('e', 't', 'a', 'o', 'i', 'n', 's', 'r', 'h', 'l', 'd', 'c', 'u', 'j', 'm', 'f', 'g', 'w', 'y', 'p', 'v', 'k', 'b', 'z', 'x', 'q')

fun histogram(text: String): Map<Char, Int> {
	val map = HashMap<Char, Int>()
	for (c in text) {
		if (c.isLetter()) {
			map.merge(c.toLowerCase(), 1) { v1, v2 -> v1 + v2 }
		}
	}
	return map
}

fun String.substitute(rules: Map<Char, Char>): String {
	val result = this.toCharArray()
	for ((index, current) in this.withIndex()) {
		if (current.isLetter()) {
			val isUpper = current.isUpperCase()
			var subChar = rules[current.toLowerCase()]
			if (isUpper) subChar = subChar?.toUpperCase()
			subChar?.let { result[index] = it }
		}
	}
	return String(result)
}

fun substitute(subText: String, ruleSet: Map<Char, Char>) {
	println(subText)
	val histogram = histogram(subText)
			.entries
			.sortedBy { it.value }
			.asReversed()
	println(histogram)

	println(letterFrequencies)
	val message = text.substitute(ruleSet)
	println(message)
}
