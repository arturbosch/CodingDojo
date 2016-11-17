def divides(n, by):
    return n % by == 0


def fizz_or_buzz(n):
    if divides(n, 15):
        return "fizzbuzz"
    elif divides(n, 5):
        return "buzz"
    elif divides(n, 3):
        return "fizz"
    else:
        return n


def fizzbuzz(n):
    [print(fizz_or_buzz(i)) for i in range(n)]


fizzbuzz(100)
