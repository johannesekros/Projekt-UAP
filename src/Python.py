from pathlib import Path
import pandas as pd
import numpy as np
import subprocess
import matplotlib.pyplot as plt
from scipy import stats
import io

project_root = Path(__file__).parent.parent

structures = ["BST", "LinkedList", "MinHeap"]
datasets   = ["Small", "Medium", "Large"]
operations = ["insert", "search", "delete"]

def confidence_interval(data, confidence=0.95):
    mean    = np.mean(data)
    std_err = stats.sem(data)
    return stats.t.interval(confidence=confidence, df=len(data)-1, loc=mean, scale=std_err)

# Samla medelvärden och konfidensintervall
# results[struktur][dataset][operation] = {"mean": float, "ci": (low, high)}
results = {s: {d: {o: {"means": []} for o in operations} for d in datasets} for s in structures}

for _ in range(10):
    for structure in structures:
        result = subprocess.run(
            ["java", "-cp", "bin", "App", structure],
            capture_output=True, text=True, cwd=project_root
        )
        df = pd.read_csv(io.StringIO(result.stdout), header=None,
                         names=["struktur", "dataset", "operation", "tid"])

        for dataset in datasets:
            for operation in operations:
                mask = (
                    (df["struktur"]  == structure) &
                    (df["dataset"]   == dataset)   &
                    (df["operation"] == operation)
                )
                mean = np.mean(df[mask]["tid"])
                results[structure][dataset][operation]["means"].append(mean)

# Plotta ett diagram per operation
for operation in operations:
    plt.figure(figsize=(8, 5))

    for structure in structures:
        means = [np.mean(results[structure][d][operation]["means"]) for d in datasets]
        cis   = [confidence_interval(results[structure][d][operation]["means"]) for d in datasets]
        errors = [(ci[1] - ci[0]) / 2 for ci in cis]

        plt.errorbar(datasets, means, yerr=errors, label=structure, capsize=5, marker="o")

    plt.title(f"{operation.capitalize()} — jämförelse")
    plt.xlabel("Dataset")
    plt.ylabel("Tid (ns)")
    plt.legend()
    plt.tight_layout()
    plt.savefig(project_root / f"plot_{operation}.pdf")
    plt.show()
