import json
import unittest
import pytest


class Person:
    """Documentation"""

    def __init__(self, name, age=0):
        self.name = name
        self.age = age
        # self.money = Money(0, 0)

    def greet(self):
        print("Hello, name is", self.name)


class Money:

    def __init__(self, euro, cents):
        self.euro = euro
        self.cents = cents

    def __str__(self):
        return str(self.euro) + " euro and " + str(self.cents) + " cents"


person = Person("Artur", 26)
person.greet()

# person.money = Money(100, 0)
# print(person.money)

dump = json.dumps(person.__dict__)
print(dump)


class PersonTest(unittest.TestCase):

    def test(self):
        tester = Person("Artur", 26)
        self.assertEqual(tester.name, "Artur")
        self.assertEqual(tester.age, 27)


unittest.main()
