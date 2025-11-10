import re
import pandas as pd
import numpy as np
import math
from collections import Counter
from Levenshtein import distance as levenshtein_distance

# --- Utility functions ---

def shannon_entropy(s):
    if not s:
        return 0
    counts = Counter(s)
    probabilities = [freq / len(s) for freq in counts.values()]
    return -sum(p * math.log2(p) for p in probabilities)

def jaccard_similarity(s1, s2):
    set1, set2 = set(s1), set(s2)
    if not set1 and not set2:
        return 1.0
    return len(set1 & set2) / len(set1 | set2)

def digit_ratio(s):
    return sum(c.isdigit() for c in s) / len(s) if s else 0

def upper_ratio(s):
    return sum(c.isupper() for c in s) / len(s) if s else 0

def symbol_ratio(s):
    return sum(not c.isalnum() for c in s) / len(s) if s else 0


# --- Parse the text file ---

def parse_records(filename):
    with open(filename, 'r', encoding='utf-8') as f:
        text = f.read()

    # Split records by "Record #"
    raw_records = re.split(r'Record\s+#\d+:', text)
    parsed = []

    for rec in raw_records:
        if not rec.strip():
            continue

        # Extract fields using regex
        y1 = re.search(r'Y₁:\s*(.+)', rec)
        y2 = re.search(r'Y₂:\s*(.+)', rec)
        z = re.search(r'Z:\s*(.*)', rec)
        sim = re.search(r'Similarity\s*=\s*([0-9.]+)', rec)

        if not (y1 and y2 and sim):
            continue  # Skip malformed records

        y1, y2 = y1.group(1).strip(), y2.group(1).strip()
        z = z.group(1).strip() if z else ''
        similarity = float(sim.group(1))

        parsed.append((y1, y2, z, similarity))

    return parsed


# --- Compute metrics and build DataFrame ---

def build_feature_dataset(records):
    data = []
    for y1, y2, z, sim in records:
        len_ratio = len(y1) / len(y2) if len(y2) else 0
        lev_dist = levenshtein_distance(y1, y2)
        jaccard = jaccard_similarity(y1, y2)
        ent1, ent2 = shannon_entropy(y1), shannon_entropy(y2)
        dig1, dig2 = digit_ratio(y1), digit_ratio(y2)
        upp1, upp2 = upper_ratio(y1), upper_ratio(y2)
        sym1, sym2 = symbol_ratio(y1), symbol_ratio(y2)
        z_len = len(z)

        data.append({
            "Y1": y1,
            "Y2": y2,
            "Z": z,
            "len_Y1": len(y1),
            "len_Y2": len(y2),
            "len_Z": z_len,
            "similarity": sim,
            "length_ratio": len_ratio,
            "levenshtein_distance": lev_dist,
            "jaccard_similarity": jaccard,
            "entropy_Y1": ent1,
            "entropy_Y2": ent2,
            "digit_ratio_Y1": dig1,
            "digit_ratio_Y2": dig2,
            "upper_ratio_Y1": upp1,
            "upper_ratio_Y2": upp2,
            "symbol_ratio_Y1": sym1,
            "symbol_ratio_Y2": sym2
        })
    return pd.DataFrame(data)


# --- Run the pipeline ---

if __name__ == "__main__":
    input_file = "CraftRiseEntireSetSimScores.txt"   # <-- path to your formatted data
    records = parse_records(input_file)
    df = build_feature_dataset(records)
    df.to_csv("ai_dataset.csv", index=False)
    print(f"Parsed {len(df)} records.")
    print("💾 AI dataset saved as ai_dataset.csv")
