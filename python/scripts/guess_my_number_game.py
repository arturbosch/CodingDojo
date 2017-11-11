import random


def convert_input(user_input):
    try:
        upper_bound = int(user_input)
        print("Ok the upper bound will be", upper_bound)
        return upper_bound
    except ValueError:
        print("You have entered an invalid input. We will just use 100 then!")
        return 100


def start_game():
    print("Lets play! Give me a number...")
    upper_bound_string = input("Enter a number: ")
    upper_bound = convert_input(upper_bound_string)

    randint = random.randint(1, upper_bound)

    tries = 3
    won = False
    while tries > 0:
        guess = int(input(str(tries) + " tries left. Guess my number: "))
        if guess == randint:
            print("You guessed my number. It was ", randint, "!")
            won = True
            break
        print(guess, "is wrong.")
        tries = tries - 1

    if not won:
        print("Sorry you lose! My number was ", randint, "!")


start_game()
