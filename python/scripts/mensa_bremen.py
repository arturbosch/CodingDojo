#!/usr/bin/env python3

import sys
import argparse
import time
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

    @property
    def is_vegan(self):
        return self.food_type.lower() == "vegan"

    def __str__(self):
        return self.day + ", " + self.category + \
               " - " + self.title + " - " + self.food_type + \
               " - " + self.price_students + "/" + self.price_employees


today = time.localtime(time.time())[6]  # Day of Week
DAYS = 7
food_plan_day_lookup_table = {
    'food-plan-0': today,
    'food-plan-1': (today + 1) % DAYS,
    'food-plan-2': (today + 2) % DAYS,
    'food-plan-3': (today + 3) % DAYS,
    'food-plan-4': (today + 4) % DAYS,
    'food-plan-5': (today + 7) % DAYS,  # exclude saturday and sunday
    'food-plan-6': (today + 8) % DAYS,
    'food-plan-7': (today + 9) % DAYS,
    'food-plan-8': (today + 10) % DAYS,
    'food-plan-9': (today + 11) % DAYS,
    'Everyday': -1
}
day_of_week_lookup = {
    0: 'Monday',
    1: 'Tuesday',
    2: 'Wednesday',
    3: 'Thursday',
    4: 'Friday',
    5: 'Monday',  # to be sure as DAYS = 7
    6: 'Tuesday',
}


def lookup_day(food_plan_string):
    day_of_week = food_plan_day_lookup_table.get(food_plan_string, -1)
    day_string = day_of_week_lookup.get(day_of_week, 'Everyday')
    return day_string


def extract_meals(page):
    meals = []
    for plan in page.find_all('div', {'class': 'food-plan'}):
        plan_id = plan.get('id', 'Everyday')
        day = lookup_day(plan_id)
        find_meals_in_plan(day, meals, plan)
    return meals


def find_meals_in_plan(day, meals, plan):
    for table in plan.find_all('table', {'class': 'food-category'}):
        food_category = table.find('thead').find('tr').find(
            'th').text.strip().replace('\n', "")
        food_category = filterUnnecessarySpaces(food_category)
        table_body = table.find('tbody')
        food_type = find_food_type(table_body)
        food_title = table_body.find(
            'td', {'class': 'field field-name-field-description'}).text \
            .replace('\n', ' ').strip()
        food_title = filterUnnecessarySpaces(food_title)
        food_price_student = find_price(table_body, 'students')
        food_price_employees = find_price(table_body, 'employees')
        meals.append(Meal(day, food_category, food_type, food_title,
                          food_price_student, food_price_employees))


def find_price(table_body, who):
    price_tag = table_body.find('td', {
        'class': 'field field-name-field-price-' + who})
    if price_tag is not None:
        food_price = price_tag.text.strip()
    else:
        food_price = "unspecified"
    return food_price


def find_food_type(table_body):
    img_tag = table_body.find('img')
    if img_tag is not None:
        food_type = img_tag['title'].strip()
    else:
        food_type = 'unspecified'
    return food_type


def filterUnnecessarySpaces(string):
    return ' '.join(string.split())


def main(mensa='', is_vegan=False, exclude_everydays=False):
    html = urllib.request.urlopen(
        'https://www.stw-bremen.de/de/essen-trinken/' +
        mensen.get(mensa, 'uni-mensa')).read()

    # with open(mensen.get(mensa, 'uni-mensa') + ".html") as file:
    #     html = file.read()

    soup = BeautifulSoup(html, "html.parser")
    soup.prettify()

    meals = extract_meals(soup)
    if is_vegan:
        meals = [it for it in meals if it.is_vegan]
    if exclude_everydays:
        meals = [it for it in meals if it.day != 'Everyday']
    for meal in meals:
        print(meal)


mensen = {
    'mensa': 'uni-mensa',
    'gw2': 'cafeteria-gw2',
    'nw1': 'mensa-nw-1'
}


def string2bool(arg):
    if arg.lower() in ('yes', 'true', 't', 'y', '1'):
        return True
    elif arg.lower() in ('no', 'false', 'f', 'n', '0'):
        return False
    else:
        raise argparse.ArgumentTypeError('Boolean value expected.')


if __name__ == '__main__':
    args = sys.argv
    arg_size = len(args)
    if arg_size <= 1:
        main()
    elif arg_size == 2:
        main(args[1])
    elif arg_size == 3:
        main(args[1], string2bool(args[2]))
    elif arg_size >= 4:
        main(args[1], string2bool(args[2]), string2bool(args[3]))
