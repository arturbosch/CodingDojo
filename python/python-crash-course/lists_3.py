locations = ["Sydney", "Tokio", "Budapest", "Moskau", "Amsterdam"]

for location in locations:
    print("I like to visit", location)

for i in range(1, 21):
    print(i)

numbers = [i for i in range(1, 10000001)]
print(min(numbers))
print(max(numbers))
print(sum(numbers))

for i in range(1, 20, 2):
    print(i)

print()
[print(i * 3) for i in range(1, 20)]
print()
[print(i ** 3) for i in range(1, 10)]
