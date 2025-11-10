import csv
import random
import pandas as pd
import numpy as np

from difflib import SequenceMatcher

# --- CONFIG ---
positive_file = "craftrise_encrytped.txt"
negative_file = "CraftRiseNegativePairs_half.csv"
combined_file = "CraftRiseCombinedPairs_Featured.csv"

# --- UTILS ---

def lcs_length(s1, s2):
    """Compute longest common substring length."""
    matcher = SequenceMatcher(None, s1, s2)
    return max((block.size for block in matcher.get_matching_blocks()), default=0)

def levenshtein(a, b):
    """Compute Levenshtein distance (simple dynamic programming)."""
    if len(a) < len(b):
        return levenshtein(b, a)
    if len(b) == 0:
        return len(a)
    prev_row = range(len(b) + 1)
    for i, c1 in enumerate(a):
        curr_row = [i + 1]
        for j, c2 in enumerate(b):
            insertions = prev_row[j + 1] + 1
            deletions = curr_row[j] + 1
            substitutions = prev_row[j] + (c1 != c2)
            curr_row.append(min(insertions, deletions, substitutions))
        prev_row = curr_row
    return prev_row[-1]

def jaccard_similarity(s1, s2):
    set1, set2 = set(s1), set(s2)
    inter = len(set1 & set2)
    union = len(set1 | set2)
    return inter / union if union > 0 else 0

# --- STEP 1: Load positives ---
positive_rows = []
with open(positive_file, "r", encoding="utf-8") as f:
    for line in f:
        parts = line.strip().split("\t")
        if len(parts) >= 3:
            pw1, pw2 = parts[1], parts[2]
            positive_rows.append([pw1, pw2, 1])

# --- STEP 2: Load negatives ---
negative_rows = []
with open(negative_file, "r", encoding="utf-8") as f:
    reader = csv.reader(f)
    for row in reader:
        # Expect: email, pw1, pw2, label(0)
        if len(row) >= 3:
            pw1, pw2, label = row[1], row[2], 0
            negative_rows.append([pw1, pw2, label])

# --- STEP 3: Combine ---
combined_data = positive_rows + negative_rows
random.shuffle(combined_data)
df = pd.DataFrame(combined_data, columns=["Password1", "Password2", "Label"])

# --- STEP 4: Add attributes ---
df["Len1"] = df["Password1"].str.len()
df["Len2"] = df["Password2"].str.len()
df["LenDiff"] = abs(df["Len1"] - df["Len2"])

df["LCS_Length"] = [
    lcs_length(p1, p2) for p1, p2 in zip(df["Password1"], df["Password2"])
]
df["LCS_Ratio"] = df["LCS_Length"] / df[["Len1", "Len2"]].max(axis=1)

df["Levenshtein"] = [
    levenshtein(p1, p2) for p1, p2 in zip(df["Password1"], df["Password2"])
]
df["Shared_Chars"] = [
    len(set(p1) & set(p2)) for p1, p2 in zip(df["Password1"], df["Password2"])
]
df["Jaccard_Sim"] = [
    jaccard_similarity(p1, p2) for p1, p2 in zip(df["Password1"], df["Password2"])
]

# --- STEP 5: Save final dataset ---
df.to_csv(combined_file, index=False)
print(f"✅ Combined + featured dataset created with {len(df)} rows → {combined_file}")
