def matches(this, that):
    if this == that:
        print("SUCCESS")
    else:
        print("FAIL: '", this, "' does not match '", that, "'!")

matches(5, 5)
matches(5, 10)
matches(5, "")
