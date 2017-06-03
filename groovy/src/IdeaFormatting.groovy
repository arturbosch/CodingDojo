/**
 * @author Artur Bosch
 */

if (args.size() < 3) throw new IllegalArgumentException("Enter idea path, project path, code style path and mask.")

def pathToIdea = args[0]
def pathToProject = args[1]
def pathToCodeStyle = args[2]
def mask = args[3]

String[] args = ["$pathToIdea/bin/format.sh", "-r", pathToProject, "-s", pathToCodeStyle, "-m", mask]

println("Starting process with args: $args")

def process = Runtime.runtime.exec(args)

def inputs = new BufferedReader(new InputStreamReader(process.inputStream)).readLines().join("\n")
def errors = new BufferedReader(new InputStreamReader(process.errorStream)).readLines().join("\n")

println(inputs)
println(errors)

process.closeStreams()
