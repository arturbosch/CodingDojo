# Lists

people = ["Artur", "Chris", "Lukas", "Janina"]

print(people[0])
print(people[1])
print(people[2])
print(people[3])

print("I go climbing with " + people[1])
print("I go climbing with " + people[2])
print("I go climbing with " + people[3])

people.append("Xenia")
people.insert(0, "Anatoliy")
print(people)

people.insert(3, "Galina")
print(people)

print(people.remove("Janina"))
print(people)

print(people.pop())
print(people.pop())
print(people.pop())
print(people.pop())
print(people)

people.remove("Anatoliy")
people.remove("Artur")
print(people)
