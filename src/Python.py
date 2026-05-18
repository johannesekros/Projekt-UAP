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

# Kör Java en gång per struktur och samla rådata
dfs = []
for structure in structures:
    result = subprocess.run(
        ["java", "-cp", "bin", "App", structure],
        capture_output=True, text=True, cwd=project_root
    )
    if result.stderr:
        print(f"=== {structure} stderr ===")
        print(result.stderr[:500])
    df = pd.read_csv(io.StringIO(result.stdout), header=None,
                     names=["struktur", "dataset", "operation", "tid"])
    dfs.append(df)

df_all = pd.concat(dfs, ignore_index=True)

# En plot per operation med 3 subplots (ett per dataset)
for operation in operations:
    fig, axes = plt.subplots(1, 3, figsize=(15, 5))
    fig.suptitle(f"{operation.capitalize()}", fontsize=14)

    for ax, dataset in zip(axes, datasets):
        for structure in structures:
            mask = (
                (df_all["struktur"]  == structure) &
                (df_all["dataset"]   == dataset)   &
                (df_all["operation"] == operation)
            )
            tider = df_all[mask]["tid"].values
            ax.plot(range(len(tider)), tider, label=structure, alpha=0.8)

        ax.set_title(dataset)
        ax.set_xlabel("Mätnummer")
        ax.set_ylabel("Tid (ns)")
        ax.legend()

    plt.tight_layout()
    plt.savefig(project_root / f"plot_{operation}.pdf")
    plt.show()
