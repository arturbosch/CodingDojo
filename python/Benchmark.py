import time
import LinesOfCode


def measure(block):
    start = time.clock()
    block()
    return time.clock() - start


def benchmark(block):
    measures = [measure(block) for _ in range(10)]
    return sum(measures) / 10


millis = benchmark(lambda: LinesOfCode.lines_of_code("./LinesOfCode.py"))
print("Time: ", millis, "ms")
