# ================================
# Password Similarity Classifier
# ================================

import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import classification_report, confusion_matrix, accuracy_score
import joblib  # for saving the model

# --- Load feature dataset ---
data_file = "CraftRiseFeaturePairs.csv"
df = pd.read_csv(data_file)

# --- Features & target ---
# Drop non-feature columns if any (like Password1, Password2, Label remains target)
feature_cols = [c for c in df.columns if c not in ['Password1', 'Password2', 'Label']]
X = df[feature_cols]
y = df['Label']

# --- Train/Test split ---
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42, stratify=y)

# --- Model training ---
clf = RandomForestClassifier(n_estimators=200, max_depth=10, random_state=42)
clf.fit(X_train, y_train)

# --- Evaluate ---
y_pred = clf.predict(X_test)
print("Accuracy:", accuracy_score(y_test, y_pred))
print("\nClassification Report:\n", classification_report(y_test, y_pred))
print("\nConfusion Matrix:\n", confusion_matrix(y_test, y_pred))

# --- Save model ---
model_file = "password_similarity_model.pkl"
joblib.dump(clf, model_file)
print(f"Trained model saved to {model_file}")


# ================================
# Optional: Predict interactively
# ================================
def predict_pair(pw1, pw2):
    # Compute same features as training
    import Levenshtein
    from difflib import SequenceMatcher

    def lcs_length(a, b):
        return int(SequenceMatcher(None, a, b).find_longest_match(0, len(a), 0, len(b)).size)

    len1, len2 = len(pw1), len(pw2)
    lcs_len = lcs_length(pw1, pw2)
    lev_dist = Levenshtein.distance(pw1, pw2)
    shared_chars = len(set(pw1) & set(pw2))
    jaccard_sim = shared_chars / len(set(pw1) | set(pw2)) if len(set(pw1) | set(pw2)) > 0 else 0
    lcs_ratio = lcs_len / max(len1, len2) if max(len1, len2) > 0 else 0

    feature_vector = [[len1, len2, abs(len1 - len2), lcs_len, lcs_ratio, lev_dist, shared_chars, jaccard_sim]]
    pred = clf.predict(feature_vector)
    prob = clf.predict_proba(feature_vector)
    return pred[0], prob[0]

# Example usage:
# result, probability = predict_pair("Password123", "Password321")
# print("Same user?", result, "Probability:", probability)
