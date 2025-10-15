import re
import pandas as pd

file_a = "CraftRiseGrouped.txt"
file_b = "YPLeaks.txt"

data_a = {}
with open(file_a, encoding="utf-8") as f:
    for line in f:
        parts = line.strip().split('\t')
        if len(parts) == 3:
            email, username, password = parts
            data_a[email.strip()] = password.strip()

data_b = {}
with open(file_b, encoding="utf-8") as f:
    content = f.read()
    entries = re.findall(r"email=([^\s]+).*?password=([^\s]+)", content, flags=re.DOTALL)
    for email, password in entries:
        email = email.strip()
        password = password.strip()
        data_b[email] = password

common_emails = set(data_a.keys()) & set(data_b.keys())

records = []
for email in common_emails:
    records.append({
        "email": email,
        "password_file_a": data_a[email],
        "password_file_b": data_b[email]
    })

df = pd.DataFrame(records)
df.to_csv("synthetic_matched.csv", index=False)
print(f"Matched {len(df)} records saved to synthetic_matched.csv")
