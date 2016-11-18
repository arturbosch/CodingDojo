def lines_of_code(path):
    with open(path) as file:
        lines = [line for line in file.readlines() if is_not_blank(line)]
        return len(lines)


def is_not_blank(line):
    return line and line.strip()


def is_no_comment(line):
    return line.startswith("#")


def is_no_import_or_module(line):
    return line.startswith("import") or line.startswith("from") or line.startswith("module")


if __name__ == "__main__":
    path = "./FizzBuzz.py"
    loc = lines_of_code(path)
    print("File: ", path.split(sep="/")[-1], " - LOC: ", loc)
