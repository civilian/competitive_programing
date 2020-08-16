#!/bin/python3

import math
import os
import random
import re
import sys
from urllib.request import urlopen
import json
from urllib.parse import urlencode
from pprint import pprint

#https://linuxconfig.org/how-to-perform-http-requests-with-python-part-1-the-standard-library

def url_query(url, query_params):
    query_string = urlencode(query_params)
    url = f"{url}?{query_string}"
    return url

def get_json_of_url(url):
    with urlopen(url) as response:
        json_response = json.loads(response.read())
    return json_response

def return_dictionary_from_args(*args, **kwargs):
    dict_ = {
        **kwargs
    }
    return dict_

def getTotalGoals(url_c, winner, year,competition):
    query_params = return_dictionary_from_args(year=year, team1=winner, page=1, competition=competition)
    url_competition = url_query(url_c, query_params)
    number_of_pages = get_json_of_url(url_competition)["total_pages"]
    number_of_goals = 0
    for i in range(1, number_of_pages + 1):
        query_params = return_dictionary_from_args(year=year, team1=winner, page=i, competition=competition)
        url_match = url_query(url_c, query_params)
        page_of_matches = get_json_of_url(url_match)
        for match in page_of_matches['data']:
            number_of_goals += int(match['team1goals'])
    
    query_params = return_dictionary_from_args(year=year, team2=winner, page=1, competition=competition)
    url_competition = url_query(url_c, query_params)
    number_of_pages = get_json_of_url(url_competition)["total_pages"]
    for i in range(1, number_of_pages + 1):
        query_params = return_dictionary_from_args(year=year, team2=winner, page=i, competition=competition)
        url_match = url_query(url_c, query_params)
        page_of_matches = get_json_of_url(url_match)
        for match in page_of_matches['data']:
            number_of_goals += int(match['team2goals'])
    return number_of_goals


def getWinnerTotalGoals(competition, year):
    query_params_competiton = {
        "year": year,
        "name": competition,
    }
    url_c = "https://jsonmock.hackerrank.com/api/football_competitions"
    url_competition = url_query(url_c, query_params_competiton)
    winner = get_json_of_url(url_competition)['data'][0]['winner']

    url_c = "https://jsonmock.hackerrank.com/api/football_matches"
    total_goals = getTotalGoals(url_c, winner, year, competition)
    return total_goals


if __name__ == '__main__':