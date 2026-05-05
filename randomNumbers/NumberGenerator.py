import numpy as np


numbers = np.random.randint(1, 9999, size=100000)

np.savetxt("DataLarge.txt", numbers, fmt="%d")