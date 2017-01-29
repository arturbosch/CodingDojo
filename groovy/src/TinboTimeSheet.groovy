import groovy.transform.Canonical

import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @author Artur Bosch
 */
class TinboTimeSheet {

	static def formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
	static def weekFormatter = DateTimeFormatter.ofPattern("E W dd.MM.yyyy")
	static def USAGE =
			"Use groovy script.groovy [startDate/as/dd.MM.yyyy] [endDate/as/dd.MM.yyyy] [category] [path/to/tinbo/time]"

	static void main(String... args) {
		if (args.length != 4) throw new IllegalArgumentException(USAGE)
		def period = new DatePeriod(LocalDate.parse(args[0], formatter), LocalDate.parse(args[1], formatter))
		def category = args[2]
		def path = Paths.get(args[3])
		if (Files.notExists(path)) throw new IllegalAccessException(USAGE)

		period.printRange()

		Collection<TinboEntry> entries = Files.lines(path)
				.filter { period.isBetween(parseDate(it)) }
				.map { TinboEntry.create(it) }
				.filter { it.category == category }
				.collect()

		entries.stream()
				.map { it.format() }
				.each { println it }

		def sumMinutes = entries.stream()
				.mapToInt { it.minutes }
				.sum()

		println ""
		println "Total: " + (sumMinutes / 60).toInteger() + "h" + sumMinutes % 60 + "m"
	}

	private static LocalDate parseDate(String it) {
		LocalDate.parse(it.substring(it.lastIndexOf(";") + 1))
	}

	@Canonical
	static class DatePeriod {
		def start
		def end

		def printRange() {
			LocalDate current = start
			println "Date period:\n"
			println current.format(weekFormatter)
			while (current != end) {
				current = current.plusDays(1L)
				println current.format(weekFormatter)
			}
			println ""
		}

		def isBetween(date) {
			date.isBefore(end) && date.isAfter(start)
		}
	}

	@Canonical
	static class TinboEntry {
		def category
		def message
		int minutes
		LocalDate date

		def format() {
			"${date.format(weekFormatter)} - ${formatMinutes()} - $message"
		}

		private def formatMinutes() {
			def hours = (minutes / 60).toInteger()
			def mins = minutes % 60
			"${hours}h${mins}m"
		}

		static def create(String line) {
			def sections = line.split(";")
			def seconds = sections[4].toInteger()
			def hours = sections[2].toInteger()
			def minutes = sections[3].toInteger()
			def total = hours * 60 + minutes + (seconds > 50 ? 1 : 0)
			new TinboEntry(
					category: sections[0],
					message: sections[1],
					minutes: total,
					date: LocalDate.parse(sections[-1])
			)
		}
	}

}
