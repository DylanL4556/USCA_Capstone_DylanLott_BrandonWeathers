import pandas as pd
from difflib import SequenceMatcher
import Levenshtein  # pip install python-Levenshtein

# --- Input / Output files ---
input_file = "CraftRiseCombinedPairs.csv"
output_file = "CraftRiseFeaturePairs.csv"

# --- Load CSV ---
df = pd.read_csv(input_file)

# --- Feature functions ---
def lcs_length(a, b):
    """Return length of the Longest Common Subsequence."""
    return int(SequenceMatcher(None, a, b).find_longest_match(0, len(a), 0, len(b)).size)

def compute_features(row):
    pw1, pw2 = str(row['Password1']), str(row['Password2'])
    len1, len2 = len(pw1), len(pw2)
    lcs_len = lcs_length(pw1, pw2)
    lev_dist = Levenshtein.distance(pw1, pw2)
    shared_chars = len(set(pw1) & set(pw2))
    jaccard_sim = shared_chars / len(set(pw1) | set(pw2)) if len(set(pw1) | set(pw2)) > 0 else 0
    lcs_ratio = lcs_len / max(len1, len2) if max(len1, len2) > 0 else 0

    return pd.Series({
        'Len1': len1,
        'Len2': len2,
        'LenDiff': abs(len1-len2),
        'LCS_Length': lcs_len,
        'LCS_Ratio': lcs_ratio,
        'Levenshtein': lev_dist,
        'Shared_Chars': shared_chars,
        'Jaccard_Sim': jaccard_sim
    })

# --- Apply feature computation ---
feature_df = df.apply(compute_features, axis=1)

# --- Combine original data with features ---
df_combined = pd.concat([df, feature_df], axis=1)

# --- Save to new CSV ---
df_combined.to_csv(output_file, index=False)
print(f"Feature-enriched dataset saved to {output_file}")
