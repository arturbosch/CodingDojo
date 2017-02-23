import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @author Artur Bosch
 */
class LicenceHeader {

	static void main(String... args) {
		if (args.size() != 3) throw new IllegalArgumentException("Usage: [path/to/licence/file] [path/to/project/root] " +
				"[endings separated by comma eg. java,groovy,kotlin]")

		String header = args[0]
		String project = args[1]
		String[] endings = args[2].split("[,;:.]").collect { ".$it" }

		def licence = new String(Paths.get(header).readBytes())
		applyLicence(licence, Paths.get(project), endings)
	}

	static void applyLicence(String licence, Path projectRoot, String[] endings) {
		Files.walk(projectRoot)
				.filter { Files.isRegularFile(it) }
				.filter { path -> endings.any { path.toString().endsWith(it) } }
				.forEach { path ->
			println("Writing licence header to $path")
			def newContent = licence + "\n\n" + new String(path.readBytes())
			Files.write(path, newContent.getBytes())
		}
	}
}

class LicenceHeaderTest {

	static void main(String... args) {
		def header = new LicenceHeader()
		def path = Paths.get("/home/artur/Repos/CodingDojo/groovy")
		header.applyLicence("/*\n * This is the new header!!!\n */", path, ["java", "groovy"].toArray(new String[0]))
	}
}