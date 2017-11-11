def guess_color(alien_color):
    if 'green' == alien_color:
        print("The Alien's color is green. You won 5 points!")
    elif 'yellow' == alien_color:
        print("The Alien's color is yellow. You won 10 points!")
    elif 'red' == alien_color:
        print("The Alien's color is red. You won 15 points!")
    else:
        print("Wrong. But you still get 1 point!")


guess_color('red')
guess_color('yellow')
guess_color('green')
guess_color('blue')


def stage_of_life(age):
    if age < 2:
        return 'baby'
    elif age < 4:
        return 'toddler'
    elif age < 13:
        return 'kid'
    elif age < 20:
        return 'teenager'
    elif age < 65:
        return 'adult'
    else:
        return 'elder'


print(stage_of_life(26))
print(stage_of_life(88))

fruits = ['apple', 'banana', 'mango']
if 'apple' in fruits:
    print("Mhhh apples!")
if 'kiwi' not in fruits:
    print("No kiwi for you!")
