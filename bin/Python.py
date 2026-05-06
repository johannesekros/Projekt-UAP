import doubleLinkedList
import MinHeap
import pandas as pd
import numpy as np

import matplotlib as mp

df_small = pd.read_csv("DataSmall.txt", header=None, names=["Värde"])
df_medium = pd.read_csv("DataMedium.txt", header=None, names=["Värde"])
df_large = pd.read_csv("DataLarge.txt", header=None, names=["Värde"])

