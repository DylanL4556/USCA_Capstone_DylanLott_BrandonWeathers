import re
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np

# === Step 1: Parse the file ===
def parse_similarity_scores(filename):
    scores = []
    pattern = re.compile(r"Similarity=([\d.]+)")
    with open(filename, "r", encoding="utf-8") as f:
        for line in f:
            match = pattern.search(line)
            if match:
                scores.append(float(match.group(1)))
    return np.array(scores)

# === Step 2: Load and visualize ===
def visualize_scores(scores, save_path="CraftRiseSimilarityDistribution.jpg"):
    plt.figure(figsize=(10, 6))
    sns.histplot(scores, bins=20, kde=True, color="skyblue", edgecolor="black")
    plt.title("Distribution of Password Similarity Scores", fontsize=16)
    plt.xlabel("Similarity Score")
    plt.ylabel("Frequency")
    plt.grid(alpha=0.3)
    plt.tight_layout()
    plt.savefig(save_path, dpi=300)
    plt.show()

if __name__ == "__main__":
    scores = parse_similarity_scores("CraftRiseEntireSetSimScores.txt")
    print(f"Parsed {len(scores)} similarity scores.")
    print(f"Mean: {scores.mean():.4f}, Median: {np.median(scores):.4f}, Std: {scores.std():.4f}")
    visualize_scores(scores)
