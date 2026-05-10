from pathlib import Path
import pandas as pd
import numpy as np
import subprocess
import matplotlib.pyplot as plt
import io

project_root = Path(__file__).parent.parent

result_bst = subprocess.run(
    ["java", "-cp", "bin", "App", "BST"], 
    capture_output=True, 
    text=True,
    cwd=project_root 
    )
print(result_bst.stdout)
df_bst = pd.read_csv(io.StringIO(result_bst.stdout), header= None, names=["struktur", "dataset", "operation", "tid"])

result_dll = subprocess.run(
    ["java", "-cp", "bin", "App", "LinkedList"], 
    capture_output=True, 
    text=True,
    cwd= project_root
    )
print(result_dll.stdout)
df_dll = pd.read_csv(io.StringIO(result_dll.stdout), header= None, names=["struktur", "dataset", "operation", "tid"])


result_minh = subprocess.run(
    ["java", "-cp", "bin", "App", "MinHeap"], 
    capture_output=True, 
    text=True,
    cwd= project_root
    )
print(result_minh.stdout)
df_minh = pd.read_csv(io.StringIO(result_minh.stdout), header= None, names=["struktur", "dataset", "operation", "tid"])


#Stor dataframe med allt
df = pd.concat([df_bst, df_dll, df_minh])

#Filtrerade plottar
df_insert = df[df["operation"]== "insert"]
df_search = df[df["operation"] == "search"]
df_delete = df[df["operation"] == "delete"]

bst_insert = df_insert[df_insert["struktur"]=="BST"]
x = bst_insert["dataset"]
y = bst_insert["tid"]
plt.figure()
plt.plot(x, y, label=["BST"])
plt.xlabel("BST")
plt.ylabel("Tid (ns)")
plt.title("Insert")
plt.legend()
plt.show()

bst_delete = df_delete[df_delete["struktur"]=="BST"]
x = bst_insert["dataset"]
y = bst_insert["tid"]
plt.figure()
plt.plot(x, y, label=["BST"])
plt.xlabel("BST")
plt.ylabel("Tid (ns)")
plt.title("Delete")
plt.legend()
plt.show()

bst_search= df_search[df_search["struktur"] == "BST"]
x = bst_insert["dataset"]
y = bst_insert["tid"]
plt.figure()
plt.plot(x, y, label=["BST"])
plt.xlabel("BST")
plt.ylabel("Tid (ns)")
plt.title("Search")
plt.legend()
plt.show()