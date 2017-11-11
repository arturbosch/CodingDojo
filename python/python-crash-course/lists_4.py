locations = ["Sydney", "Tokio", "Budapest", "Moskau", "Amsterdam"]

print("The first three slices are: ", locations[:3])
print("The middle three slices are: ", locations[1:4])
print("The last three slices are: ", locations[2:])
print()

locations_copy = locations.copy()
print(locations_copy)

locations.append("Berlin")
locations_copy.append("Kopenhagen")
print(locations_copy)