#!/usr/bin/env python

import urllib.request
from bs4 import BeautifulSoup


class Meal:

    def __init__(self, day, category, food_type, title,
                 price_students, price_employees):
        self.day = day
        self.price_students = price_students
        self.price_employees = price_employees
        self.title = title
        self.food_type = food_type
        self.category = category

    def is_vegan(self):
        return self.food_type.lower() == "vegan"

    def __str__(self):
        return self.__dict__.__str__()


def lookup_day(day_string):
    if day_string == 'food-plan-0':
        return "Monday"
    elif day_string == 'food-plan-1':
        return "Tuesday"
    elif day_string == 'food-plan-2':
        return "Wednesday"
    elif day_string == 'food-plan-3':
        return "Thursday"
    elif day_string == 'food-plan-4':
        return "Friday"
    else:
        return "Everyday"


def extract_meals(page):
    meals = []
    for plan in page.find_all('div', {'class': 'food-plan'}):
        day = lookup_day(plan['id'])
        for table in plan.find_all('table', {'class': 'food-category'}):
            food_category = table.find('thead').find('tr').find(
                'th').text.strip()
            table_body = table.find('tbody')
            food_type = table_body.find('img')['title'].strip()
            food_title = table_body.find('td', {
                'class': 'field field-name-field-description'}).text.replace(
                '\n', ' ').replace(' ', '').strip()  # FixMe: Spaces
            food_price_student = table_body.find('td', {
                'class': 'field field-name-field-price-students'}).text.strip()
            food_price_employees = table_body.find('td', {
                'class': 'field field-name-field-price-employees'}).text.strip()
            meals.append(
                Meal(day, food_category, food_type, food_title,
                     food_price_student, food_price_employees))
    return meals


def main():
    html = urllib.request.urlopen(
        'https://www.stw-bremen.de/de/essen-trinken/uni-mensa').read()

    # with open("mensa_example.html") as file:
    #     html = file.read()

    soup = BeautifulSoup(html, "html.parser")
    soup.prettify()
    meals = extract_meals(soup)
    # [print(it) for it in meals if it.is_vegan()]
    [print(it) for it in meals if it.is_vegan() and it.day != 'Everyday']


main()
