from sys import stdout as out


def print_names(names):
    [print(it) for it in names]


names = ["Peter", "Pan", "Puddy", "Plump"]
print_names(names)


def make_great(names):
    return ["The Great " + it for it in names]


def make_great_mutate_list(names):
    for idx, it in enumerate(names):
        names[idx] = "The Great " + it


print_names(make_great(names))
print_names(names)

# make_great_mutate_list(names)
make_great_mutate_list(names[:])
print_names(names)


# varargs

def my_sandwich(*topings):
    out.write("I like my sandmich with")
    [out.write(" " + it) for it in topings]


my_sandwich("hummus", "tomata", "rucola")
print()


# varpairs

def build_profile(firstname, lastname, **additional_info):
    profile = {'firstname': firstname, 'lastname': lastname}
    for key, value in additional_info.items():
        profile[key] = value
    return profile


print(build_profile('Artur', 'Bosch', age=26, location='Bremen'))
