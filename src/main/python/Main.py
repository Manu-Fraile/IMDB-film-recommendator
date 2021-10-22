#!/usr/bin/python3.8
import sys
import pandas as pd
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import CountVectorizer


df = pd.DataFrame()

for line in sys.stdin:
    parsed = line.replace("[", "").replace("]", "").rsplit(',',1)
    toAppend = pd.DataFrame([parsed], columns=["title", "bag_of_words"])
    df = df.append(toAppend)

df.set_index('title', inplace=True)
print(df)

# instantiating and generating the count matrix
count = CountVectorizer()
count_matrix = count.fit_transform(df['bag_of_words'])

# generating the cosine similarity matrix
cosine_sim = cosine_similarity(count_matrix, count_matrix)

# creating a Series for the movie titles so they are associated to an ordered numerical
# list I will use in the function to match the indexes
indices = pd.Series(df.index)

    #  defining the function that takes in movie title
    # as input and returns the top 10 recommended movies
def recommendations(title, cosine_sim = cosine_sim):

    # initializing the empty list of recommended movies
    recommended_movies = []

    # gettin the index of the movie that matches the title
    idx = indices[indices == title].index[0]

    # creating a Series with the similarity scores in descending order
    score_series = pd.Series(cosine_sim[idx]).sort_values(ascending = False)

    # getting the indexes of the 10 most similar movies
    top_10_indexes = list(score_series.iloc[1:11].index)

    # populating the list with the titles of the best 10 matching movies
    for i in top_10_indexes:
        recommended_movies.append(list(df.index)[i])

    return recommended_movies

print(recommendations('The Strangers'))
