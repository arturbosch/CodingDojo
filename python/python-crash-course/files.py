with open("text.txt") as file:
    for line in file.readlines():
        print(line)

with open("guests.txt", "w") as file:
    name = input("Enter your name: ")
    file.write(name)
