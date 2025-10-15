
from collections import defaultdict

input_file = "CraftRiseGrouped.txt"
output_file = "CraftRiseDupes.txt"

email_passwords = defaultdict(list)

with open(input_file, "r", encoding="utf-8") as f:
    for line in f:
        parts = line.strip().split("\t")
        if len(parts) == 3:
            email, username, password = parts
            email_passwords[email.strip()].append(password.strip())

duplicates = {email: pw_list for email, pw_list in email_passwords.items() if len(pw_list) > 1}

with open(output_file, "w", encoding="utf-8") as f:
    for email, pw_list in duplicates.items():
        for pw in pw_list:
            f.write(f"{email}\t{pw}\n")

print(f"Found {len(duplicates)} duplicate emails. Saved to {output_file}")
